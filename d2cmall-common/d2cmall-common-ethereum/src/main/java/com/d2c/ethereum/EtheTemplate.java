package com.d2c.ethereum;

import java.math.BigInteger;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.ethereum.config.SystemProperties;
import org.ethereum.core.AccountState;
import org.ethereum.core.Block;
import org.ethereum.core.BlockchainImpl;
import org.ethereum.core.Genesis;
import org.ethereum.core.Repository;
import org.ethereum.core.Transaction;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.crypto.ECKey;
import org.ethereum.db.ByteArrayWrapper;
import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;
import org.ethereum.mine.BlockMiner;
import org.ethereum.solidity.compiler.CompilationResult;
import org.ethereum.solidity.compiler.CompilationResult.ContractMetadata;
import org.ethereum.solidity.compiler.SolidityCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d2c.common.base.exception.BaseException;
import com.d2c.ethereum.listener.MyEthereumListener;
import com.d2c.ethereum.listener.MyMinerListener;
import com.d2c.ethereum.model.TransactionBean;
import com.d2c.ethereum.utils.EtheUt;

/**
 * 以太坊区块链操作
 * @author wull
 */
public class EtheTemplate {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Ethereum ethereum;
	
	private BlockMiner miner;
	
	private MyEthereumListener etheListener;
	
	private MyMinerListener minerListener;
	
	private SolidityCompiler compiler = SolidityCompiler.getInstance();
	
	/**
	 * 初始化 Ethereum
	 */
    public void init(){
    	logger.info("Ethereum 初始化 ..");
        ethereum = EthereumFactory.createEthereum();
        
    	logger.info("EthereumListener 初始化 ..");
    	miner = ethereum.getBlockMiner();
    	
    	//添加监听
    	etheListener = new MyEthereumListener(ethereum);
        ethereum.addListener(etheListener);
        
        minerListener = new MyMinerListener();
        miner.addListener(minerListener);
        
        //启动挖矿
        miner.startMining();
        
    }
    
    /**
     * 创建账号
     */
    public ECKey createAccount(){
        return new ECKey();
    }

    /**
     * 获取账号
     */
    public AccountState getAccount(String addr){
        return getRepository().getAccountState(EtheUt.toHex(addr));
    }

    /**
     * 查看账户金额
     */
    public BigInteger getBalance(String addr){
    	if(StringUtils.isBlank(addr)){
    		return BigInteger.ZERO;
    	}
    	return getRepository().getBalance(EtheUt.toHex(addr));
    }

    /**
     * 添加账户金额
     */
    public BigInteger addBalance(String addr, String value){
    	return getRepository().addBalance(EtheUt.toHex(addr), EtheUt.toBigInt(value));
    }
    

    /**
     * 发起交易
     */
    public TransactionReceipt sendTxAndWait(ECKey sender, String receiver, Long gas, Long value, byte[] data){
    	BigInteger nonce = getRepository().getNonce(sender.getAddress());
    	
//    	byte[] data = getContractData(contract);
    	
    	TransactionBean tx = new TransactionBean(EtheUt.toByte(nonce), EtheUt.toByte(ethereum.getGasPrice()), EtheUt.toByte(gas),
    			EtheUt.toHex(receiver), EtheUt.toByte(value), data, ethereum.getChainIdForNextBlock());
    	tx.setSender(sender.getAddress());
        tx.sign(ECKey.fromPrivate(sender.getPrivKeyBytes()));
    	logger.info("开始提交交易:" + tx);
    	Future<Transaction> future = ethereum.submitTransaction(tx);
    	logger.info("提交交易完成, 等待交易确认.. isDone: " + future.isDone() + "\n 交易:" + tx);
    	
        try {
			return waitForTx(tx.getHash());
		} catch (InterruptedException e) {
			return null;
		}
//    	return null;
    }

    public ContractMetadata getContractData(String contract) {
		try{
			logger.info("Compiling contract...");
	    	SolidityCompiler.Result result = compiler.compileSrc(contract.getBytes(), true, true,
	                SolidityCompiler.Options.ABI, SolidityCompiler.Options.BIN);
	        if (result.isFailed()) {
	            throw new RuntimeException("Contract compilation failed:\n" + result.errors);
	        }
	        CompilationResult res = CompilationResult.parse(result.output);
	        if (res.getContracts().isEmpty()) {
	            throw new RuntimeException("Compilation failed, no contracts returned:\n" + result.errors);
	        }
	        ContractMetadata metadata = res.getContracts().iterator().next();
	        if (metadata.bin == null || metadata.bin.isEmpty()) {
	            throw new RuntimeException("Compilation failed, no binary returned:\n" + result.errors);
	        }
	        return  metadata;
		}catch (Exception e) {
			logger.error("合同编译异常失败...", e);
			throw new BaseException("合同编译异常失败...", e);
		}
	}

    private TransactionReceipt waitForTx(byte[] txHash) throws InterruptedException {
        ByteArrayWrapper txHashW = new ByteArrayWrapper(txHash);
        etheListener.cleanTxRec(txHashW);
        long startBlock = countBlock();
        while(true) {
            TransactionReceipt receipt = etheListener.getTxRec(txHashW);
            if (receipt != null) {
            	logger.info("交易确认返回...TransactionReceipt:" + receipt);
                return receipt;
            } else {
                long curBlock = countBlock();
                if (curBlock > startBlock + 16) {
                    throw new RuntimeException("最近的16个区块没有该交易返回: " + txHashW.toString().substring(0,8));
                } else {
                    logger.info("交易确认等待区块返回 0x" + txHashW.toString().substring(0,8) +
                            " (" + (curBlock - startBlock) + " 条区块已建立) ...");
                }
            }
            synchronized (this) {
                wait(20000);
            }
        }
    }

    /**
     * 设置挖矿金额返回地址
     */
    public void setMinerCoinbase(byte[] minerCoinbase){
    	getBlockchain().setMinerCoinbase(minerCoinbase);
    }
    

    /**
     * 获得链
     */
    public BlockchainImpl getBlockchain(){
        return (BlockchainImpl) ethereum.getBlockchain();
    }

    /**
     * 获得第{num}区块
     */
    public Block getBlockByNumber(long num){
        return getBlockchain().getBlockByNumber(num);
    }

    /**
     * 获得最新区块
     */
    public Block getBestBlock(){
        return getBlockchain().getBestBlock();
    }

    /**
     * 总链数量
     */
    public Long countBlock(){
        return getBestBlock().getNumber();
    }

    /**
     * 开始挖矿
     */
    public void startMining(){
    	logger.info("开始挖矿startMining..");
    	miner.startMining();
    }

    /**
     * 停止挖矿
     */
    public void stopMining(){
    	logger.info("停止挖矿stopMining..");
        miner.stopMining();
    }

    /**
     * 账户资源
     */
    public Repository getRepository(){
    	return (Repository) ethereum.getRepository();
    }
    
    /**
     * 创世区块
     */
    public Genesis getGenesis(){
    	return getSystemProperties().getGenesis();
    }

    /**
     * 配置文件
     */
    public SystemProperties getSystemProperties(){
    	return SystemProperties.getDefault();
    }
}

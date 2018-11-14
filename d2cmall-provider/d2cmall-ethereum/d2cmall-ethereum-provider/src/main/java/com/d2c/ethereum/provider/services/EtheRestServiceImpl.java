package com.d2c.ethereum.provider.services;

import org.ethereum.core.AccountState;
import org.ethereum.core.CallTransaction;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.crypto.ECKey;
import org.ethereum.solidity.compiler.CompilationResult.ContractMetadata;
import org.ethereum.vm.program.ProgramResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.ethereum.EtheTemplate;
import com.d2c.ethereum.api.services.EtheRestService;
import com.d2c.ethereum.utils.EtheUt;

/**
 * REST服务
 * @author wull
 */
@Service(protocol = { "dubbo", "rest" })
public class EtheRestServiceImpl implements EtheRestService {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EtheTemplate etheTemplate;
	
	private ECKey sender;
	private String receiver = "d0487b571998c573103f90f9873f5f2d17e46811";
	
	private String contract =
            "contract Sample {" +
            "  int i;" +
            "  function inc(int n) {" +
            "    i = i + n;" +
            "  }" +
            "  function get() returns (int) {" +
            "    return i;" +
            "  }" +
            "}";

	@Override
	public Object test() {
		
		ContractMetadata meta = etheTemplate.getContractData(contract);

//		TransactionReceipt receipt = etheTemplate.sendTxAndWait(sender, receiver, 100000L, 10L, Hex.decode(meta.bin));
		TransactionReceipt receipt = etheTemplate.sendTxAndWait(sender, null, 3000000L, 0L, Hex.decode(meta.bin));
		
        if (!receipt.isSuccessful()) {
            logger.error("交易失败，原因: " + receipt.getError());
            return null;
        }
		
		byte[] contractAddress = receipt.getTransaction().getContractAddress();
        logger.info("合同创建，合同地址: " + Hex.toHexString(contractAddress));

        logger.info("调用合同方法 'inc'");
        CallTransaction.Contract contract = new CallTransaction.Contract(meta.abi);
        CallTransaction.Function inc = contract.getByName("inc");
        byte[] functionCallBytes = inc.encode(777);
        TransactionReceipt receipt1 = etheTemplate.sendTxAndWait(sender, Hex.toHexString(contractAddress), 3000000L,  0L, functionCallBytes);
        if (!receipt1.isSuccessful()) {
            logger.error("调用合同方法失败，原因: " + receipt.getError());
            return null;
        }
        logger.info("合同修改成功!");

        ProgramResult r = etheTemplate.ethereum.callConstantFunction(Hex.toHexString(contractAddress),
                contract.getByName("get"));
        Object[] ret = contract.getByName("get").decodeResult(r.getHReturn());
        logger.info("调用合同方法 'get' , 合同内数值为: " + ret[0]);
		
		return receipt;
	}

    public ECKey getSender() {
    	if(sender == null){
    		sender = etheTemplate.createAccount();
    	}
		return sender;
	}

	/**
     * 数据描述
     */
    public String info(){
    	String s = "区块链信息:\n";
		etheTemplate.setMinerCoinbase(getSender().getAddress());
		
    	s += "sender public: " + getSender().toString()  + "\n";
    	s += "sender Private: " + getSender().toStringWithPrivate()  + "\n";
    	s += "sender addr: " + getAddress()  + "\n";
    	s += "sender 金额:" + etheTemplate.getBalance(getAddress()) + "\n";
    	s += "receiver 账户: " + receiver  + "\n";
    	s += "receiver 金额:" + etheTemplate.getBalance(receiver) + "\n";
    	s += "区块链长: " + etheTemplate.countBlock() + "\n";
    	s += "最新区块: " + etheTemplate.getBestBlock() + "\n";
    	
    	logger.info(s);
    	return s;
    }

	private String getAddress() {
		return EtheUt.toHexString(getSender().getAddress());
	}

    /**
     * 开始挖矿
     */
	@Override
    public Long startMining(){
    	etheTemplate.startMining();
    	return etheTemplate.countBlock();
    }

    /**
     * 总链数量
     */
	@Override
    public Long countBlock(){
    	return etheTemplate.countBlock();
    }

    /**
     * 创建账号
     */
	@Override
    public Object createAccount(){
    	return etheTemplate.createAccount();
    }

    /**
     * 查询账号
     */
	@Override
    public String getAccount(String addr){
		AccountState acc = etheTemplate.getAccount(addr);
		logger.info("查询账号 - " + addr + ": " + acc);
		if(acc == null){
			return "null";
		}
    	return acc.toString();
    }

    /**
     * 添加账户金额
     */
    public String addBalance(String addr, String value){
    	etheTemplate.addBalance(addr, value);
    	return getAccount(addr);
    }

}

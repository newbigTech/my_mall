package com.d2c.ethereum.listener;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ethereum.core.Block;
import org.ethereum.core.PendingState;
import org.ethereum.core.Transaction;
import org.ethereum.core.TransactionExecutionSummary;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.db.ByteArrayWrapper;
import org.ethereum.facade.Ethereum;
import org.ethereum.listener.EthereumListenerAdapter;
import org.ethereum.net.eth.message.StatusMessage;
import org.ethereum.net.message.Message;
import org.ethereum.net.p2p.HelloMessage;
import org.ethereum.net.rlpx.Node;
import org.ethereum.net.server.Channel;
import org.ethereum.util.BIUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d2c.ethereum.utils.EtheUt;

public class MyEthereumListener extends EthereumListenerAdapter {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Ethereum ethereum;
    private boolean syncDone = false;
    
    private Map<ByteArrayWrapper, TransactionReceipt> txWaiters =
            Collections.synchronizedMap(new HashMap<ByteArrayWrapper, TransactionReceipt>());

    public MyEthereumListener(Ethereum ethereum) {
        this.ethereum = ethereum;
    }

    @Override
    public void onBlock(Block block, List<TransactionReceipt> receipts) {
    	logger.info("区块创建 onBlock ...block: " + block.getNumber() + " syncDone: " + syncDone + " 交易数:" + receipts.size());
        
        //交易返回
        for (TransactionReceipt receipt : receipts) {
        	byte[] hash = receipt.getTransaction().getHash();
        	
        	logger.info("交易返回 ...txHash: " + EtheUt.toHexString(hash) + "\n txRec: " + receipt);
            ByteArrayWrapper txHashW = new ByteArrayWrapper(hash);
            if (txWaiters.containsKey(txHashW)) {
                txWaiters.put(txHashW, receipt);
                synchronized (this) {
                    notifyAll();
                }
            }
        }
        
        if (syncDone){
        	calcNetHashRate(block);
        }
        
    }

    /**
     *  同步完成时
     */
    @Override
    public void onSyncDone(SyncState state) {
    	logger.info("同步完成 onSyncDone ... state: " + state);
        if (!syncDone) {
            syncDone = true;
        	logger.info("区块同步完成...syncDone: " + syncDone);
        }
    }
    
    /**
     * 在新的未决事务或新的最佳块接收上的变更状态
     * 当一个新的交易到达时，它将在当前未决状态之上执行
     * 当一个新的最佳块到达时，挂起状态被调整到新的存储库状态
     * 所有待处理的事务都在新挂起状态的顶部执行。
     */
    @Override
    public void onPendingStateChanged(PendingState pendingState) {
    	logger.info("未决事务或新块上状态变更...");
    	pendingState.getPendingTransactions().forEach(tx -> {
        	logger.info("交易数据..." + tx);
    	});
    }

    /**
     * 当挂起事务到达，执行或删除，并包含到一个块中
     * @param tx收据在当前的挂起状态下收到tx执行的收据
     * @param 状态当前状态
     * @param 阻塞当前未决状态所基于的块（用于未决的tx状态）或者被包含在（包括状态）的那个块
     */
    @Override
    public void onPendingTransactionUpdate(TransactionReceipt txReceipt, PendingTransactionState state, Block block) {
    	logger.info("挂起交易到达... 状态: " + state + "\n TransactionReceipt:" + txReceipt + "\n block:" + block);
    }

    @Override
    public void onRecvMessage(Channel channel, Message message) {
    }

    @Override
    public void onSendMessage(Channel channel, Message message) {
    }

    @Override
    public void onPeerDisconnect(String host, long port) {
    }

    @Override
    public void onPendingTransactionsReceived(List<Transaction> transactions) {
    }

    @Override
    public void onHandShakePeer(Channel channel, HelloMessage helloMessage) {

    }

    @Override
    public void onNoConnections() {

    }


    @Override
    public void onVMTraceCreated(String transactionHash, String trace) {

    }

    @Override
    public void onNodeDiscovered(Node node) {

    }

    @Override
    public void onEthStatusUpdated(Channel channel, StatusMessage statusMessage) {

    }

    @Override
    public void onTransactionExecuted(TransactionExecutionSummary summary) {

    }

    @Override
    public void onPeerAddedToSyncPool(Channel peer) {

    }
    
    public TransactionReceipt getTxRec(ByteArrayWrapper txHashW){
    	return txWaiters.get(txHashW);
    }
    
    public void putTxRec(ByteArrayWrapper txHashW, TransactionReceipt txRec){
    	txWaiters.put(txHashW, txRec);
    }
    
    public void cleanTxRec(ByteArrayWrapper txHashW){
    	txWaiters.put(txHashW, null);
    }
    
    //***********************************************

    /**
     * Just small method to estimate total power off all miners on the net
     * @param block
     */
    private void calcNetHashRate(Block block){

        if ( block.getNumber() > 1000){

            long avgTime = 1;
            long cumTimeDiff = 0;
            Block currBlock = block;
            for (int i=0; i < 1000; ++i){

                Block parent = ethereum.getBlockchain().getBlockByHash(currBlock.getParentHash());
                long diff = currBlock.getTimestamp() - parent.getTimestamp();
                cumTimeDiff += Math.abs(diff);
                currBlock = parent;
            }

            avgTime = cumTimeDiff / 1000;

            BigInteger netHashRate = block.getDifficultyBI().divide(BIUtil.toBI(avgTime));
            double hashRate = netHashRate.divide(new BigInteger("1000000000")).doubleValue();
            
            logger.info("矿工计算力: " + hashRate + " GH/s");
        }

    }

}

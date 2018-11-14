package com.d2c.ethereum.listener;

import org.ethereum.core.Block;
import org.ethereum.mine.MinerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMinerListener implements MinerListener {
	
	protected static final Logger logger = LoggerFactory.getLogger(MyMinerListener.class);

	@Override
	public void miningStarted() {
		logger.info("启动挖矿...");
	}

	@Override
	public void miningStopped() {
		logger.info("停止挖矿...");
	}

	@Override
	public void blockMiningStarted(Block block) {
		logger.info("挖矿中...区块:" + block);
	}

	@Override
	public void blockMined(Block block) {
		logger.info("已经成功挖到矿区块...");
	}

	@Override
	public void blockMiningCanceled(Block block) {
		logger.info("放弃挖矿区块:" + block);
	}

}

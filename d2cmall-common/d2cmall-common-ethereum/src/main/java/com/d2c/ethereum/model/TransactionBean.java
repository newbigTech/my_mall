package com.d2c.ethereum.model;

import org.ethereum.core.Transaction;

public class TransactionBean extends Transaction {

	public TransactionBean(byte[] nonce, byte[] gasPrice, byte[] gasLimit, byte[] receiver, byte[] value,
			byte[] data, Integer chainId) {
		super(nonce, gasPrice, gasLimit, receiver, value, data, chainId);
	}
	
	public void setSender(byte[] sender){
		this.sendAddress = sender;
	}

}

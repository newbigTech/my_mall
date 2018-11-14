package com.d2c.ethereum.utils;

import java.math.BigInteger;

import org.ethereum.util.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.util.encoders.Hex;

/**
 * 以太坊区块链工具
 * @author wull
 */
public class EtheUt {
	
	static final Logger logger = LoggerFactory.getLogger(EtheUt.class);
	
	/**
	 * 转 Hex
	 */
	public static byte[] toHex(String val){
		return Hex.decode(val != null ? val : "");
	}
	
	public static String toHexString(byte[] data){
		return Hex.toHexString(data);
	}

	/**
	 * 转 byte
	 */
	public static byte[] toByte(String val){
		if(val == null) val = "";
		return val.getBytes();
	}
	
	public static byte[] toByte(BigInteger val){
		return ByteUtil.bigIntegerToBytes(val);
	}
	
	public static byte[] toByte(long val){
		return ByteUtil.longToBytesNoLeadZeroes(val);
	}
	
	/**
	 * 转 BigInteger
	 */
	public static BigInteger toBigInt(String val){
		if(val == null) return BigInteger.ZERO;
		try {
			return new BigInteger(val);
		} catch (Exception e) {
			return BigInteger.ZERO;
		}
	}

}

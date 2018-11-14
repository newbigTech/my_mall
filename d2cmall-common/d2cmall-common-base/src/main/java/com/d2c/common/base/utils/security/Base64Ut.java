package com.d2c.common.base.utils.security;

import org.apache.commons.codec.binary.Base64;

import com.d2c.common.base.exception.BaseException;
import com.d2c.common.base.utils.ByteUt;

/**
 * Base64编码
 */
public class Base64Ut {
	
	public final static String CHARSET = "ISO-8859-1";
	
	/**
	 * Base64编码
	 */
	public static String encode(String data) {
		return encode(ByteUt.toByteISO(data));
	}
	
	public static String encode(byte[] data) {
		return ByteUt.toStrISO(encodeByte(data));
	}
	
	public static byte[] encodeByte(byte[] data) {
		try {
			return Base64.encodeBase64URLSafe(data);
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}

	/**
	 * Base64解码
	 */
	public static String decode(String data) {
		return decode(ByteUt.toByteISO(data));
	}
	
	public static String decode(byte[] data) {
		return ByteUt.toStrISO(decodeByte(data));
	}
	
	public static byte[] decodeByte(String data) {
		return decodeByte(ByteUt.toByteISO(data));
	}
	
	public static byte[] decodeByte(byte[] data) {
		try {
			return Base64.decodeBase64(data);
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}

}

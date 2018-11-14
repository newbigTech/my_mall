package com.d2c.order.third.payment.wxpay.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import com.d2c.order.third.payment.wxpay.sign.WeixinSign;

public class WxPayUtil {

	public static String createNoncestr() {
		return UUID.randomUUID().toString().substring(0, 32);
	}

	public static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/** 签名生成 */
	public static String createSign(HashMap<String, String> map, String pay_key) {
		return WeixinSign.md5Sign(FormatBizQueryParaMap(map), pay_key);
	}

	public static String FormatBizQueryParaMap(HashMap<String, String> paraMap) throws RuntimeException {
		List<String> keys = new ArrayList<String>(paraMap.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = paraMap.get(key).toString();
			// key = key.toLowerCase();
			if (i == keys.size() - 1) {
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	public static String mapToXml(HashMap<String, String> arr) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			if (val.contains(" ")) {
				sb.append("<").append(key).append("><![CDATA[").append(val).append("]]></").append(key).append(">");
			} else {
				sb.append("<").append(key).append(">").append(val).append("</").append(key).append(">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	public static boolean IsNumeric(String str) {
		if (str.matches("\\d *")) {
			return true;
		} else {
			return false;
		}
	}

}

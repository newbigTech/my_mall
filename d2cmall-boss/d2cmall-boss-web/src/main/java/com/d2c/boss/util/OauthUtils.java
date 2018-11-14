package com.d2c.boss.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OauthUtils {

	public static final String SECRET_KEY = "d2cd2c6d7da05cc8874640ba6aac060192d2cd2c";// 签名密匙

	public static String BuildMysign(Map sArray, String key) {
		String prestr = createLinkString(sArray); // 排序，把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		prestr = prestr + key; // 把拼接后的字符串再与安全校验码直接连接起来
		String mysign = Md5Encrypt.md5(prestr);
		return mysign;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key).toString();
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	public static Map<String, String> parasFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	public static boolean checkSign(Map<String, String> params) {
		Boolean result = false;
		String sign = params.get("sign");
		if (sign == null || "".equals(sign)) {
			result = false;
		} else {
			params.remove("sign");
			String mySign = OauthUtils.BuildMysign(params, SECRET_KEY);
			if (mySign.equals(sign)) {
				result = true;
			}
		}
		return result;

	}

	public static Map<String, String> transStringToMap(String mapString) {
		Map<String, String> map = new HashMap<String, String>();
		String[] arraydata = mapString.split("&");// 按“，”将其分为字符数组
		for (int i = 0; i < arraydata.length; i++) {
			int j = arraydata[i].indexOf("=");
			map.put(arraydata[i].substring(0, j), arraydata[i].substring(j + 1, arraydata[i].length()));
		}
		return map;
	}
}

package com.d2c.boss.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.d2c.boss.util.Base64;

public class BossApiOauth {
	// public static final String D2C_HTTP = "http://183.129.242.178:8099";//
	// 官网后台域名
	public static final String D2C_HTTP = "http://192.168.5.19:8097";//
	// 官网后台域名
	public static final String SECRET_KEY = "d2cd2c6d7da05cc8874640ba6aac060192d2cd2c";// 签名密匙

	public static Map<String, Map<String, String>> roles = new HashMap<String, Map<String, String>>();

	private static BossApiOauth bossApiOauth = null;

	private Map<String, String> params;

	private String apiPath;

	private BossApiOauth() {

	}

	public static BossApiOauth getInstance() {
		if (bossApiOauth == null) {
			bossApiOauth = new BossApiOauth();
		}
		return bossApiOauth;
	}

	public Map<String, String> getParams(String apiPath, String partnerCode) {
		params = new HashMap<String, String>();
		this.apiPath = apiPath;
		if (partnerCode == null)
			return null;
		return params;
	}

	private String getEndCodeParam(Map<String, String> param) throws UnsupportedEncodingException {
		Map<String, String> sPara = OauthUtils.parasFilter(param);
		String mySign = OauthUtils.BuildMysign(sPara, SECRET_KEY);
		sPara.put("sign", mySign);
		String params = Base64.encode(OauthUtils.createLinkString(sPara).getBytes("UTF-8"));
		return params;
	}

	public JSONObject invoke() throws Exception {
		if (params.size() == 0) {
			return null;
		}
		String endCodeString = getEndCodeParam(params);
		CloseableHttpResponse response2 = postJson(D2C_HTTP + apiPath, endCodeString);
		JSONObject responseData = null;
		try {
			if (response2.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity2 = response2.getEntity();
				responseData = getJosn(entity2.getContent());
				EntityUtils.consume(entity2);
			}
		} finally {
			response2.close();
		}
		return responseData;
	}

	public JSONObject getJosn(InputStream inputStream) throws IOException, JSONException {
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		if (!StringUtils.hasText(sb)) {
			sb.append("{}");
		}
		return new JSONObject(sb.toString());
	}

	public CloseableHttpResponse postJson(String url, String json) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
		StringEntity se = new StringEntity(json, "UTF-8");
		se.setContentType("text/json");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		httpPost.setEntity(se);
		return httpclient.execute(httpPost);
	}
}

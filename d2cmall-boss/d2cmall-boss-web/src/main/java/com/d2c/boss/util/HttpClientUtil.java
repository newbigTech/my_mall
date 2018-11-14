package com.d2c.boss.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class HttpClientUtil {
	/**
	 * 发送get请求
	 */
	public static JSONObject sendGetHttps(String url) {
		try {
			HttpClient client = new DefaultHttpClient();
			client = WebClientDevWrapper.wrapClient(client);
			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
				return new JSONObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

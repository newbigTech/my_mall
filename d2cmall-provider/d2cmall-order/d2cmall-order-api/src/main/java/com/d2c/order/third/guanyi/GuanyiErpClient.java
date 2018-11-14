package com.d2c.order.third.guanyi;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.d2c.common.core.helper.SpringHelper;
import com.d2c.order.property.OrderProperties;

public class GuanyiErpClient {

	private static final String url = "http://v2.api.guanyierp.com/rest/erp_open"; // http://v2.api.guanyiyun.com/rest/erp_open

	public static final String DELIVERY_CODE = "gy.erp.trade.deliverys.get";
	public static final String STOCK_CODE = "gy.erp.new.stock.get";

	private OrderProperties orderProperties;
	private static RestTemplate restTemplate = new RestTemplate();

	private static GuanyiErpClient instance = null;

	public static GuanyiErpClient getInstance() {
		if (instance == null) {
			instance = new GuanyiErpClient();
		}
		return instance;
	}

	private GuanyiErpClient() {
		orderProperties = SpringHelper.getBean(OrderProperties.class);
	}

	public JSONObject send(JSONObject param, String method) throws Exception {
		JSONObject params = new JSONObject();
		params.putAll(param);
		params.put("appkey", orderProperties.getGuanyiAPPKEY());
		params.put("sessionkey", orderProperties.getGuanyiSessionKey());
		params.put("method", method);
		String sign = sign(params.toJSONString());
		params.put("sign", sign);

		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(mediaType);
		HttpEntity<JSONObject> entity = new HttpEntity<>(params, headers);
		String json = restTemplate.postForObject(orderProperties.getGuanyiApiUrl(), entity, String.class);
		JSONObject result = JSON.parseObject(json);
		if (!result.getString("success").equals("true")) {
			throw new Exception(result.getString("errorDesc"));
		}
		return result;
	}

	private String sign(String json) {
		StringBuilder enValue = new StringBuilder();
		// 前后加上secret
		enValue.append(orderProperties.getGuanyiSecret());
		enValue.append(json);
		enValue.append(orderProperties.getGuanyiSecret());
		// 使用MD5加密(32位大写)
		String sign = null;
		try {
			sign = DigestUtils.md5Hex(enValue.toString().getBytes("UTF-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sign;
	}

	public static void main(String[] args) {
		GuanyiErpClient client = GuanyiErpClient.getInstance();
		JSONObject params = new JSONObject();
		params.put("page_no", 1);
		params.put("page_size", 20);
		params.put("delivery", 1);
		params.put("start_date", "2018-07-13 14:01:22");
		// params.put("shop_code", "D2C001");
		// params.put("delivery", 1);
		try {
			JSONObject result = client.send(params, STOCK_CODE);
			System.out.println(result.toJSONString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

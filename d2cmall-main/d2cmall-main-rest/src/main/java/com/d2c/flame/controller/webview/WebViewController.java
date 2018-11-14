package com.d2c.flame.controller.webview;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.d2c.common.base.utils.security.D2CSign;
import com.d2c.flame.controller.base.BaseController;
import com.d2c.flame.property.HttpProperties;
import com.d2c.order.third.payment.alipay.core.pcwap.AlipayBase;
import com.d2c.order.third.payment.alipay.core.pcwap.AlipayCore;
import com.d2c.order.third.payment.alipay.sgin.BASE64;

/**
 * WEB VIEW
 */
@Controller
@RequestMapping(value = "/v3/api/invoke")
public class WebViewController extends BaseController {

	@Autowired
	private HttpProperties httpProperties;

	/**
	 * APP访问网页
	 * 
	 * @param appId
	 * @param token
	 * @param url
	 * @param response
	 */
	@RequestMapping(value = "/{appId}", method = RequestMethod.GET)
	public void invoke(@PathVariable String appId, String token, @RequestParam(required = true) String url,
			HttpServletResponse response) {
		SimpleDateFormat fmt = new SimpleDateFormat("HHmmssSS");
		String timeStamp = fmt.format(new Date());
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", appId);
		map.put("url", url);
		map.put("token", token == null ? "" : token);
		map.put("timeStamp", timeStamp);
		Map<String, String> sPara = AlipayCore.parasFilter(map);
		String mySign = AlipayBase.BuildMysign(sPara, D2CSign.APP_TO_WAP_KEY);
		sPara.put("sign", mySign);
		String paramData = generatNameValuePair(sPara);
		String d2cBase;
		try {
			d2cBase = BASE64.encode(paramData.getBytes("UTF-8"));
			response.sendRedirect(httpProperties.getMobileUrl() + "/appToWap?params=" + d2cBase);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private static String generatNameValuePair(Map<String, String> properties) {
		StringBuilder params = new StringBuilder();
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			params.append(entry.getKey() + "=" + entry.getValue() + "@d2c@");
		}
		String str = params.toString();
		return str.substring(0, str.length() - 5);
	}

}

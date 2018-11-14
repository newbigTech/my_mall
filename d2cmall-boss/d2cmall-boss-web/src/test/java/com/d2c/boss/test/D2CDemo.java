package com.d2c.boss.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.json.JSONObject;

public class D2CDemo {
	/**
	 * 编号
	 */
	public static final String PARTNER_CODE = "3333";

	public static void main(String[] args) {
		/**
		 * 调用Api接口地址
		 */
		String apiPath = "/api/crm/coupondef/list";
		/**
		 * 接口需要的参数 例如：pagerSize 每页记录数;pagerNumber 当前页码;lastSysDate 修改时间
		 */

		Integer pagerSize = 10;
		Integer pagerNumber = 1;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date lastSysDate = new Date();
		try {
			lastSysDate = sf.parse("2016-11-1");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		BossApiOauth bossApiOauth = BossApiOauth.getInstance();
		// 设置接口路径和接口参数
		Map<String, String> params = bossApiOauth.getParams(apiPath, PARTNER_CODE);
		params.put("pageSize", String.valueOf(pagerSize));
		params.put("pageNumber", String.valueOf(pagerNumber));
		params.put("startModifyTime", "1477289600000");
		params.put("endModifyTime", "1478569600000");
		params.put("partnerCode", PARTNER_CODE);
		// params.put("startModifyTime", String.valueOf(lastSysDate.getTime() -
		// 640000000));
		// params.put("endModifyTime", String.valueOf(lastSysDate.getTime() +
		// 640000000));

		try {
			// 发送请求并获取json对象的结果
			JSONObject json = bossApiOauth.invoke();
			if (json != null && json.length() > 0) {
				System.out.println(json.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

package com.d2c.frame.web.control;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * web基础控制层类
 */
public class BaseControl extends SuperControl {

	/**
	 * 获取登录IP
	 */
	protected String getLoginIp() {
		HttpServletRequest request = getRequest();
		String userIp = request.getHeader("x-forwarded-for") == null ? request.getRemoteAddr()
				: request.getHeader("x-forwarded-for");
		return userIp.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : userIp;
	}

	/**
	 * 设置cache的key值
	 */
	protected String cacheKey(Object... keys) {
		String key = "";
		for(Object o : keys){
			if(o == null) continue;
			if(StringUtils.isBlank(key)){
				key = o.toString();
			}else{
				key = key + ":" + o.toString();
			}
		}
		return key;
	}
	
}

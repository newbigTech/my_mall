package com.d2c.main.openapi.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.d2c.common.core.helper.SpringHelper;
import com.d2c.openapi.api.entity.OpenUserDO;

@Component
public class OpenUserHolder {
	
	private static final String SESSION_OPEN_USER = "SESSION_OPEN_USER";
	
	private static OpenUserHolder instance;
	
	@Autowired
	private HttpSession httpSession;
	
	private ThreadLocal<OpenUserDO> userPools = new ThreadLocal<OpenUserDO>();
	
	public static OpenUserHolder getInstance(){
		if(instance == null){
			instance = SpringHelper.getBean(OpenUserHolder.class);
		}
		return instance;
	}

	public static void setOpenUser(OpenUserDO user) {
		getInstance().setUser(user);
	}

	public static OpenUserDO getOpenUser() {
		return getInstance().getUser();
	}

	public static Long getBrandId() {
		OpenUserDO user = getOpenUser();
		if (user == null) return null;
		return user.getBrandId();
	}

	public static String getBrandName() {
		OpenUserDO user = getOpenUser();
		if (user == null) return null;
		return user.getBrandName();
	}
	
	//************************************
	
	public void setUser(OpenUserDO user){
		if(httpSession != null){
			httpSession.setAttribute(SESSION_OPEN_USER, user);
		}else{
			if(userPools == null) userPools = new ThreadLocal<OpenUserDO>();
			userPools.set(user);
		}
	}

	public OpenUserDO getUser() {
		if(httpSession != null){
			return (OpenUserDO) httpSession.getAttribute(SESSION_OPEN_USER);
		}else if(userPools != null) {
			return userPools.get();
		}else{
			return null;
		}
	}

} 

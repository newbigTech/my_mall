package com.d2c.behavior.api.mongo.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.d2c.behavior.api.constant.AppConst;
import com.d2c.behavior.api.mongo.enums.AppTerminalEnum;
import com.d2c.common.mongodb.model.BaseMongoDO;

/**
 * 用户设备软件表
 * @author wull
 */
@Document
public class AppVersionDO extends BaseMongoDO {
	
	private static final long serialVersionUID = -7893408552894365890L;

	/**
	 * APP名称
	 */
	private String appName;
	
	/**
	 * APP类型
	 * @see AppTerminalEnum
	 */
	@Indexed
	private String appTerminal;

	/**
	 * 版本号
	 */
	@Indexed
	private String appVersion;
	
	@Override
	public String toString() {
		return appName + "-" + appTerminal + "-" + appVersion;
	}
	
	public AppVersionDO() {
		this.appName = AppConst.D2C_APP_NAME;
	}

	public AppVersionDO(String appTerminal, String appVersion) {
		this();
		this.appTerminal = appTerminal;
		this.appVersion = appVersion;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppTerminal() {
		return appTerminal;
	}

	public void setAppTerminal(String appTerminal) {
		this.appTerminal = appTerminal;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
	
}

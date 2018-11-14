package com.d2c.boss.member.model;

import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 子账号
 */
@Table(name = "crm_member")
public class Member extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 会员账号
	 */
	private String loginCode;
	/**
	 * 子账号
	 */
	private String account;
	/**
	 * 登陆次数
	 */
	private int loginNumber;
	/**
	 * 最后登录IP
	 */
	private String loginIp;
	/**
	 * openId
	 */
	private String openId;
	/**
	 * 最后登录日期
	 */
	private Date loginDate;
	/**
	 * 登录来源
	 */
	private String source;
	/**
	 * 绑定时间
	 */
	private Date bindDate;
	/**
	 * 第三方用户登录信息
	 */
	private String userInfo;

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getLoginNumber() {
		return loginNumber;
	}

	public void setLoginNumber(int loginNumber) {
		this.loginNumber = loginNumber;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}

package com.d2c.boss.online.model;

import java.util.Date;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.d2c.common.api.annotation.AssertColumn;
import com.d2c.common.api.model.PreUserDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * crm实体类 - 主账号
 */
@Table(name = "online_member")
public class OnlineMember extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 国家地区编号
	 */
	private String nationCode = "86";
	/**
	 * 允许登录账号(一个或以上)
	 */
	private String loginCode;
	/**
	 * Cookie值
	 */
	@JsonIgnoreProperties
	private String cookieKey;
	/**
	 * 登入次数
	 */
	private int loginNumber = 0;
	/**
	 * 最后登录IP
	 */
	@JsonIgnoreProperties
	private String loginIp;
	/**
	 * 最后登录日期
	 */
	@JsonIgnoreProperties
	private Date loginDate;
	/**
	 * 登录来源
	 */
	@JsonIgnoreProperties
	private String source;
	/**
	 * 注册的用户设备来源
	 */
	@JsonIgnoreProperties
	private String device;
	/**
	 * OPENID
	 */
	@JsonIgnoreProperties
	private String openId;
	/**
	 * 微信专用unionId
	 */
	@JsonIgnoreProperties
	private String unionId;
	/**
	 * 第三方用户登录信息
	 */
	@JsonIgnoreProperties
	private String userInfo;
	/**
	 * 账户ID
	 */
	@AssertColumn("会员ID名称不能为空")
	private Long memberInfoId;
	/**
	 * 绑定前账户ID
	 */
	private Long oldMemberInfoId;
	/**
	 * 系统创建标识
	 */
	private boolean sysCreate = false;
	/**
	 * 用户令牌 Ticket Grangting Ticket
	 */
	private String tgt;
	/**
	 * 设备标示
	 */
	private String deviceLabel;

	/**
	 * 绑定时间
	 */
	private Date bindDate;

	private int upStatus;

	/**
	 * 登录源名字
	 */
	public String getSourceName() {
		if (source == null)
			return "";
		if (source.equals("QQ"))
			return "QQ账号";
		if (source.equals("TaobaoClient"))
			return "淘宝账号";
		if (source.equals("Weibo"))
			return "新浪微博账号";
		if (source.equals("WeixinGz"))
			return "微信公众平台";
		if (source.equals("Weixin"))
			return "微信开放平台";
		return "";
	}

	public void setSourceName() {

	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getCookieKey() {
		return cookieKey;
	}

	public void setCookieKey(String cookieKey) {
		this.cookieKey = cookieKey;
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

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public boolean isSysCreate() {
		return sysCreate;
	}

	public void setSysCreate(boolean sysCreate) {
		this.sysCreate = sysCreate;
	}

	public String getTgt() {
		return tgt;
	}

	public void setTgt(String tgt) {
		this.tgt = tgt;
	}

	public String getDeviceLabel() {
		return deviceLabel;
	}

	public void setDeviceLabel(String deviceLabel) {
		this.deviceLabel = deviceLabel;
	}

	public Long getMemberInfoId() {
		return memberInfoId;
	}

	public void setMemberInfoId(Long memberInfoId) {
		this.memberInfoId = memberInfoId;
	}

	public int getLoginNumber() {
		return loginNumber;
	}

	public void setLoginNumber(int loginNumber) {
		this.loginNumber = loginNumber;
	}

	public boolean isD2c() {
		if (StringUtils.isEmpty(openId) && StringUtils.isEmpty(source)) {
			return true;
		}
		return false;
	}

	public Long getOldMemberInfoId() {
		return oldMemberInfoId;
	}

	public void setOldMemberInfoId(Long oldMemberInfoId) {
		this.oldMemberInfoId = oldMemberInfoId;
	}

	public String getNationCode() {
		return nationCode;
	}

	public void setNationCode(String nationCode) {
		if (!StringUtils.isNotBlank(nationCode)) {
			nationCode = "86";
		}
		this.nationCode = nationCode;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public int getUpStatus() {
		return upStatus;
	}

	public void setUpStatus(int upStatus) {
		this.upStatus = upStatus;
	}
}

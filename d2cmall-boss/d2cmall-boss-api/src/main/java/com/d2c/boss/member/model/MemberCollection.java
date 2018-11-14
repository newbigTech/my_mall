package com.d2c.boss.member.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.BaseParentDO;

/**
 * crm实体类 - 会员收藏商品
 */
@Table(name = "crm_member_collection")
public class MemberCollection extends BaseParentDO<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Date createDate = new Date();
	private String lastModifyMan;
	private Date modifyDate = new Date();
	private String creator = null;
	/**
	 * 会员账号
	 */
	private Long memberId;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 机器码
	 */
	private String mac;
	/**
	 * 行为类型: 1、登录，2、商品浏览， 3、店铺浏览，4、活动浏览，5、关注的店铺，6、收藏的商品，7、分享的页面，8、购物车
	 */
	private int type;
	/**
	 * 行为发生时间
	 */
	private Date transactionTime;
	/**
	 * 行为对象编号
	 */
	private String transactionId;
	/**
	 * 行为对象类型 1、设计师 2、商品
	 * 
	 */
	private int transactionType;
	/**
	 * 设备
	 */
	private String device;
	/**
	 * 版本
	 */
	private String appVersion;

	private int status;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getBehaviorTime() {
		return transactionTime;
	}

	public void setBehaviorTime(Date behaviorTime) {
		this.transactionTime = behaviorTime;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyMan() {
		return lastModifyMan;
	}

	public void setLastModifyMan(String lastModifyMan) {
		this.lastModifyMan = lastModifyMan;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

package com.d2c.boss.offline.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

@Entity()
@Table(name = "td_offline_vip")
public class OfflineVip extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Long vipId;
	/**
	 * 类型ID
	 */
	private String vipTypeId;
	/**
	 * 卡号
	 */
	@Column(unique = true)
	private String cardNo;
	/**
	 * 开卡的门店ID
	 */
	private String storeId;
	/**
	 * 开卡的门店名称
	 */
	private String storeName;
	/**
	 * 开卡的门店编号
	 */
	private String storeCode;
	/**
	 * 积分
	 */
	private int points;
	/**
	 * 卡有效期
	 */
	private Date expiryDate;
	/**
	 * 卡版本号
	 */
	private int cardVersion;
	/**
	 * 卡积分数据的版本号
	 */
	private int pointVersion;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getCardVersion() {
		return cardVersion;
	}

	public void setCardVersion(int cardVersion) {
		this.cardVersion = cardVersion;
	}

	public int getPointVersion() {
		return pointVersion;
	}

	public void setPointVersion(int pointVersion) {
		this.pointVersion = pointVersion;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Long getVipId() {
		return vipId;
	}

	public void setVipId(Long vipId) {
		this.vipId = vipId;
	}

	public String getVipTypeId() {
		return vipTypeId;
	}

	public void setVipTypeId(String vipTypeId) {
		this.vipTypeId = vipTypeId;
	}

	protected String getDisplayName() {
		return "门店会员";
	}
	
}

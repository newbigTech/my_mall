package com.d2c.boss.member.model;

import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 优惠券
 */
@Table(name = "crm_coupon")
public class Coupon extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 优惠券名称
	 */
	private String name;
	/**
	 * 优惠券编码
	 */
	private String code;
	/**
	 * 面额或者折扣（95代表９.５折）
	 */
	private int amount;
	/**
	 * （1）CASH、现金券， （2）PASSWORD、密码券， （3）DISCOUNT、折扣券
	 * 
	 */
	private String type;
	/**
	 * 优惠卷使用账号
	 */
	private String loginCode;
	/**
	 * 使用开始时间
	 */
	private Date enableDate;
	/**
	 * 使用结束时间
	 */
	private Date expireDate;
	/**
	 * 优惠券状态: UNCLAIMED：未领用， CLAIMED：未使用， LOCKED：已锁定， USED：已使用， INVALID：已过期
	 * 
	 */
	private String status;
	/**
	 * 优惠券使用说明
	 */
	private String remark;
	/**
	 * 定义id
	 */
	private Long defineId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public Date getEnableDate() {
		return enableDate;
	}

	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getDefineId() {
		return defineId;
	}

	public void setDefineId(Long defineId) {
		this.defineId = defineId;
	}

}

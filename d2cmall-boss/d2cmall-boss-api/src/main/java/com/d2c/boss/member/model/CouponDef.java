package com.d2c.boss.member.model;

import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 优惠券
 */
@Table(name = "crm_coupon_def")
public class CouponDef extends PreUserDO {

	private static final long serialVersionUID = 1L;
	/**
	 * （1）CASH、现金券， （2）PASSWORD、密码券， （3）DISCOUNT、折扣券
	 * 
	 */
	private String type;
	/**
	 * 优惠券适用范围: 1、全场, 2、指定设计师， 3、指定商品
	 */
	private String association;
	/**
	 * 优惠券名称
	 */
	private String name;
	/**
	 * 副标题
	 */
	private String subTitle;
	/**
	 * 使用开始时间
	 */
	private Date enableDate;
	/**
	 * 使用结束时间
	 */
	private Date expireDate;
	/**
	 * 使用有效天数（如果>0 可用时间开始=领用时间，失效时间=领用时间+天数）
	 */
	private Integer activeDay;
	/**
	 * 使用有效小时（如果>0 可用时间开始=领用时间，失效时间=领用时间+小时数）
	 */
	private Integer activeHour;
	/**
	 * 可以领用开始时间
	 */
	private Date claimStart;
	/**
	 * 可以领用结束时间
	 */
	private Date claimEnd;
	/**
	 * 面额或者折扣额95代表９.５折
	 */
	private Integer amount;
	/**
	 * 使用该优惠券，订单需要满足的金额
	 */
	private Integer needAmount;
	/**
	 * 发放数量（<=0不限）
	 */
	private Integer quantity = 0;

	/**
	 * 已经领取的数量(汇总字段)
	 */
	private Integer claimed = 0;
	/**
	 * 会员领用张数限制
	 */
	private Integer claimLimit = 1;
	/**
	 * 是否启用
	 */
	private Integer enable = 0;
	/**
	 * 是否随机金额
	 */
	private Integer random = 0;
	/**
	 * 优惠券使用说明
	 */
	private String remark;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
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

	public Integer getActiveDay() {
		return activeDay;
	}

	public void setActiveDay(Integer activeDay) {
		this.activeDay = activeDay;
	}

	public Integer getActiveHour() {
		return activeHour;
	}

	public void setActiveHour(Integer activeHour) {
		this.activeHour = activeHour;
	}

	public Date getClaimStart() {
		return claimStart;
	}

	public void setClaimStart(Date claimStart) {
		this.claimStart = claimStart;
	}

	public Date getClaimEnd() {
		return claimEnd;
	}

	public void setClaimEnd(Date claimEnd) {
		this.claimEnd = claimEnd;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getNeedAmount() {
		return needAmount;
	}

	public void setNeedAmount(Integer needAmount) {
		this.needAmount = needAmount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getClaimed() {
		return claimed;
	}

	public void setClaimed(Integer claimed) {
		this.claimed = claimed;
	}

	public Integer getClaimLimit() {
		return claimLimit;
	}

	public void setClaimLimit(Integer claimLimit) {
		this.claimLimit = claimLimit;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getRandom() {
		return random;
	}

	public void setRandom(Integer random) {
		this.random = random;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

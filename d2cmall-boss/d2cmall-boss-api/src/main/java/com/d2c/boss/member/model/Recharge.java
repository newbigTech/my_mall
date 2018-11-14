package com.d2c.boss.member.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 充值记录
 */
@Table(name = "crm_recharge")
public class Recharge extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private String sn;
	/**
	 * 会员账号
	 */
	private String loginCode;
	/**
	 * 充值金额，不可为空
	 */
	private BigDecimal rechargeAmount = new BigDecimal(0);
	/**
	 * 赠送金额
	 */
	private BigDecimal giftAmount = new BigDecimal(0);
	/**
	 * 充值活动名称
	 */
	private String ruleName;
	/**
	 * 充值时间（转账收到的时间） 不可为空
	 */
	private Date payDate;
	/**
	 * 充值类型：0、实付，1、 赠送
	 */
	private int payType;
	/**
	 * 状态：0、未审核，1、 已审核，-1、关闭，-2、 在线充值，待支付
	 */
	private int status;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public BigDecimal getGiftAmount() {
		return giftAmount;
	}

	public void setGiftAmount(BigDecimal giftAmount) {
		this.giftAmount = giftAmount;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

}

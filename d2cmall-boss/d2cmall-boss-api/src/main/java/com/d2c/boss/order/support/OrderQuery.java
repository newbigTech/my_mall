package com.d2c.boss.order.support;

import java.util.Date;

import com.d2c.common.api.query.model.BaseQuery;

public class OrderQuery extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @DSParam(field = "payType", description = "付款方式")
	private String payType;

	// @DSParam(field = "orderSn", description = "订单编号", op = Operator.LIKE)
	private String orderSn;

	// @DSParam(field = "store.id", description = "订单来源")
	private String id;

	// @DSParam(field = "store.code", description = "订单来源")
	private String code;

	// @DSParam(field = "status", description = "订单状态")
	private String status;

	// @DSParam(field = "paymentTime", description = "付款开始时间", op =
	// Operator.GTE)
	private Date beginPayDate;

	// @DSParam(field = "paymentTime", description = "付款开始时间", op =
	// Operator.LTE)
	private Date endPayDate;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Date getBeginPayDate() {
		return beginPayDate;
	}

	public void setBeginPayDate(Date beginPayDate) {
		this.beginPayDate = beginPayDate;
	}

	public Date getEndPayDate() {
		return endPayDate;
	}

	public void setEndPayDate(Date endPayDate) {
		this.endPayDate = endPayDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

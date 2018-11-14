package com.d2c.boss.online.support;

import com.d2c.common.api.query.model.BaseQuery;

public class OnlineOrderQuery extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @DSParam(field = "orderSn", description = "订单编号")
	private String orderSn;

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

}

package com.d2c.boss.order.support;

import com.d2c.common.api.query.model.BaseQuery;

public class OrderItemQuery extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @DSParam(field = "orderSn", description = "订单编号", op = Operator.LIKE)
	private String orderSn;

	// @DSParam(field = "skuSn", description = "条码", op = Operator.LIKE)
	private String skuSn;

	// @DSParam(field = "productName", description = "商品名称", op = Operator.LIKE)
	private String productName;

	// @DSParam(field = "status", description = "明细状态")
	private String status;

	private String storeCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getSkuSn() {
		return skuSn;
	}

	public void setSkuSn(String skuSn) {
		this.skuSn = skuSn;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

}

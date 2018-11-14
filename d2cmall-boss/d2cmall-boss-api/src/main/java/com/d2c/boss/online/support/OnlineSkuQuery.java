package com.d2c.boss.online.support;

import com.d2c.common.api.query.model.BaseQuery;

public class OnlineSkuQuery extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @DSParam(field = "barCode", description = "条码")
	private String barCode;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

}
package com.d2c.boss.online.support;

import com.d2c.common.api.query.model.BaseQuery;

public class OnlineProductQuery extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @DSParam(field = "inernalSN", description = "款号")
	private String inernalSN;

	// @DSParam(field = "d2cState", description = "官网状态")
	private Integer d2cState;

	public String getInernalSN() {
		return inernalSN;
	}

	public void setInernalSN(String inernalSN) {
		this.inernalSN = inernalSN;
	}

	public Integer getD2cState() {
		return d2cState;
	}

	public void setD2cState(Integer d2cState) {
		this.d2cState = d2cState;
	}

}

package com.d2c.order.support;

import java.math.BigDecimal;

import com.d2c.common.api.query.model.BaseQuery;

/**
 * 结算明细的结算金额
 * 
 * @author Administrator
 *
 */
public class StatementItemBean extends BaseQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderSn;
	private String barCode;
	private BigDecimal settlePrice;
	private Integer year;
	private Integer month;

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public BigDecimal getSettlePrice() {
		return settlePrice;
	}

	public void setSettlePrice(BigDecimal settlePrice) {
		this.settlePrice = settlePrice;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}

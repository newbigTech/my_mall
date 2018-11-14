package com.d2c.boss.order.dto;

import java.io.Serializable;

public class MonthQuantityDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer year;
	public Integer month;
	public Long quantity;

	public MonthQuantityDto(Integer year, Integer month, Long quantity) {
		this.year = year;
		this.month = month;
		this.quantity = quantity;
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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}

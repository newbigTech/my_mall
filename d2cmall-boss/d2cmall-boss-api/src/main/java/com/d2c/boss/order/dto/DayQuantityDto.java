package com.d2c.boss.order.dto;

import java.io.Serializable;

public class DayQuantityDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer day;
	public Long quantity;

	public DayQuantityDto(Integer day, Long quantity) {
		this.day = day;
		this.quantity = quantity;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}

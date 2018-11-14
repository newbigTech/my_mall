package com.d2c.boss.order.dto;

import java.io.Serializable;

public class WeekQuantityDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer year;
	public Integer week;
	public Long quantity;

	/**
	 * XXXX年XX月第X周 xx.xx.-xx.xx.
	 */
	public String showStr;

	public WeekQuantityDto(Integer year, Integer week, Long quantity) {
		this.year = year;
		this.week = week;
		this.quantity = quantity;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getShowStr() {
		return showStr;
	}

	public void setShowStr(String showStr) {
		this.showStr = showStr;
	}

}

package com.d2c.boss.order.dto;

import java.io.Serializable;

public class DaySalesCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String time;

	private Integer count;

	public DaySalesCount() {

	}

	public DaySalesCount(String time, Integer count) {
		this.time = time;
		this.count = count;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}

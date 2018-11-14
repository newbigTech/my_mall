package com.d2c.product.query;

import java.util.Date;

import com.d2c.common.api.query.model.BaseQuery;

public class AwardSessionSearcher extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Integer status;
	private Date beginStartDate;
	private Date endStartDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getBeginStartDate() {
		return beginStartDate;
	}

	public void setBeginStartDate(Date beginStartDate) {
		this.beginStartDate = beginStartDate;
	}

	public Date getEndStartDate() {
		return endStartDate;
	}

	public void setEndStartDate(Date endStartDate) {
		this.endStartDate = endStartDate;
	}

}

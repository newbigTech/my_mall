package com.d2c.order.query;

import com.d2c.common.api.query.model.BaseQuery;

public class CashCardDefSearcher extends BaseQuery{
	

	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private String name;
	
	private Integer status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

}

package com.d2c.product.query;

import com.d2c.common.api.query.model.BaseQuery;

public class TopCategorySearcher extends BaseQuery {

	private static final long serialVersionUID = 1L;

	private String name;

	private String code;

	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

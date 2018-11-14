package com.d2c.product.query;

import com.d2c.common.api.query.model.BaseQuery;

public class ProductCombSearcher extends BaseQuery {

	private static final long serialVersionUID = 1L;

	private String name;
	private String inernalSn;
	private Integer mark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInernalSn() {
		return inernalSn;
	}

	public void setInernalSn(String inernalSn) {
		this.inernalSn = inernalSn;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

}

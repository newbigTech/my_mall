package com.d2c.common.search.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class ParentSearchDO<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	protected ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

}

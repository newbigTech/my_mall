package com.d2c.member.dto;

import java.util.List;

import com.d2c.member.model.Resource;

public class ResourceDto extends Resource {

	private static final long serialVersionUID = 1L;

	/**
	 * 子菜单
	 */
	private List<Resource> children;

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> list) {
		this.children = list;
	}
}

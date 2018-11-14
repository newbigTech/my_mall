package com.d2c.boss.product.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 设计师分类
 */
@Table(name = "crm_brand_category")
public class BrandCategory extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private String code;
	/**
	 * 类名
	 */
	private String name;
	/**
	 * 父类编码
	 */
	private String parent;
	/**
	 * 层级
	 */
	private int depth;

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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}

package com.d2c.boss.product.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 商品分类
 */
@Table(name = "crm_product_category")
public class ProductCategory extends PreUserDO {

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
	/**
	 * 是否可用
	 */
	private int status;
	/**
	 * 全路径
	 */
	private String allCode;

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

	public String getAllCode() {
		return allCode;
	}

	public void setAllCode(String allCode) {
		this.allCode = allCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

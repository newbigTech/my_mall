package com.d2c.boss.product.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 商品标签
 */
@Table(name = "crm_product_tags")
public class ProductTags extends PreUserDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 标签名称
	 */
	private String name;
	/**
	 * 上下架 1上架 0下架
	 */
	private Integer status = 0;

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

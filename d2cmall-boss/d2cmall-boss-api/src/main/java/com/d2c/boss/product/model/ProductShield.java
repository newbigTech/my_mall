package com.d2c.boss.product.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 隐藏查询商品 TODO
 */
@Table(name = "product_shield")
public class ProductShield extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 款号
	 */
	private String sn;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	protected String getDisplayName() {
		return "隐藏查询商品";
	}

}

package com.d2c.boss.product.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 条码库存 TODO
 */
@Table(name = "base_sku_stock")
public class SkuStock extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 唯一键
	 */
	private String key;
	/**
	 * 条码
	 */
	private String barCode;
	/**
	 * 即时库存
	 */
	private Integer stock;
	/**
	 * 门店编号
	 */
	private String storeCode;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	protected String getDisplayName() {
		return "条码库存";
	}

}

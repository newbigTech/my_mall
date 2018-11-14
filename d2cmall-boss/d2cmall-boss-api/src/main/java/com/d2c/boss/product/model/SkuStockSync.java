package com.d2c.boss.product.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 条码库存 TODO
 */
@Table(name = "base_sku_stock_sync")
public class SkuStockSync extends PreUserDO {

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
	/**
	 * 版本号
	 */
	private int version = 0;
	/**
	 * 状态，-1：删除，0：不变，1：修改，2：新增
	 */
	private int status;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

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

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

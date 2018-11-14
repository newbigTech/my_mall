package com.d2c.msg.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 管易sku变更记录
 * 
 * @author Lain
 *
 */
@Table(name = "log_guanyi_sku_stock")
public class GuanyiSkuStockLog extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 条码
	 */
	private String sku;
	/**
	 * 库存
	 */
	private Integer stock;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}

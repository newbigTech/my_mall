package com.d2c.boss.product.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - SKU
 */
@Table(name = "crm_product_sku")
public class ProductSku extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 条码
	 */
	private String sn;
	/**
	 * 款号
	 */
	private String productSn;
	/**
	 * 销售属性1（颜色）
	 */
	private String sale1;
	/**
	 * 销售属性2（尺码）
	 */
	private String sale2;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public String getSale1() {
		return sale1;
	}

	public void setSale1(String sale1) {
		this.sale1 = sale1;
	}

	public String getSale2() {
		return sale2;
	}

	public void setSale2(String sale2) {
		this.sale2 = sale2;
	}

}

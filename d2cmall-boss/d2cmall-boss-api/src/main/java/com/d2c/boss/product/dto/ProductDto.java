package com.d2c.boss.product.dto;

import java.util.List;

import com.d2c.boss.product.model.Product;
import com.d2c.boss.product.model.ProductSku;

public class ProductDto extends Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * sku列表
	 */
	private List<ProductSku> productSkus;

	public List<ProductSku> getProductSkus() {
		return productSkus;
	}

	public void setProductSkus(List<ProductSku> productSkus) {
		this.productSkus = productSkus;
	}
}

package com.d2c.product.dto;

import java.util.List;

import com.d2c.product.model.FlashPromotion;
import com.d2c.product.search.model.SearcherProduct;

public class FlashPromotionDto extends FlashPromotion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SearcherProduct> products;

	public List<SearcherProduct> getProducts() {
		return products;
	}

	public void setProducts(List<SearcherProduct> products) {
		this.products = products;
	}

}

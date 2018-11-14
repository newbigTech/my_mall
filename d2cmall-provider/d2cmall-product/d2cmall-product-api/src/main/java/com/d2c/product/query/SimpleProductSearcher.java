package com.d2c.product.query;

import com.d2c.common.api.query.model.BaseQuery;
import com.d2c.product.model.Product.SaleCategory;

public class SimpleProductSearcher extends BaseQuery {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Long categoryId;

	private SaleCategory saleCategory;

	private Boolean marketable;

	public SaleCategory getSaleCategory() {
		return saleCategory;
	}

	public void setSaleCategory(SaleCategory saleCategory) {
		this.saleCategory = saleCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean getMarketable() {
		return marketable;
	}

	public void setMarketable(Boolean marketable) {
		this.marketable = marketable;
	}

}

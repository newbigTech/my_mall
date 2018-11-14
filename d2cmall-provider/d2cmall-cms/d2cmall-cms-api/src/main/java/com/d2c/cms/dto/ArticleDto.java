package com.d2c.cms.dto;

import com.d2c.cms.model.Article;

public class ArticleDto extends Article {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String brandName;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}

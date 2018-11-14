package com.d2c.chest.api.dto;

import java.util.List;

import com.d2c.chest.api.entity.SimilarRuleDO;
import com.d2c.common.api.dto.EntityDTO;

public class SimilarSchemeDTO extends EntityDTO {

	private static final long serialVersionUID = -4903284310787030896L;

	private String schemeName;
	
	private Integer categoryId;
	
	private Integer maxSize;
	
	private List<SimilarRuleDO> rules;

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public List<SimilarRuleDO> getRules() {
		return rules;
	}

	public void setRules(List<SimilarRuleDO> rules) {
		this.rules = rules;
	}

}

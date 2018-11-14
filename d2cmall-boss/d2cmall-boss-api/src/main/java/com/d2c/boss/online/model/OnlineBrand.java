package com.d2c.boss.online.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 
 * @author yangge
 * 
 */
@Entity()
@Table(name = "online_brand")
public class OnlineBrand extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 品牌编号
	 */
	private String code;
	/**
	 * 品牌名称
	 */
	private String name;
	/**
	 * 设计师名称
	 */
	private String designer;
	/**
	 * 品牌地区
	 */
	private String country;
	/**
	 * 品牌店铺
	 */
	private String shop;
	/**
	 * 品牌描述
	 */
	private String description;
	/**
	 * 一级分类
	 */
	private String topCategory;
	/**
	 * 二级分类
	 */
	private String secCategory;
	/**
	 * 品牌标签
	 */
	private String tags;
	/**
	 * A-Z类别
	 */
	private String pageGroup;

	private int upStatus;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTopCategory() {
		return topCategory;
	}

	public void setTopCategory(String topCategory) {
		this.topCategory = topCategory;
	}

	public String getSecCategory() {
		return secCategory;
	}

	public void setSecCategory(String secCategory) {
		this.secCategory = secCategory;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPageGroup() {
		return pageGroup;
	}

	public void setPageGroup(String pageGroup) {
		this.pageGroup = pageGroup;
	}

	public int getUpStatus() {
		return upStatus;
	}

	public void setUpStatus(int upStatus) {
		this.upStatus = upStatus;
	}

}

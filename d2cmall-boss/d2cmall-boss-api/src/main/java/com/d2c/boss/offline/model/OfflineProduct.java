package com.d2c.boss.offline.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

@Entity()
@Table(name = "td_offline_product")
public class OfflineProduct extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * D2C编号
	 */
	private String inernalSN;
	/**
	 * 外部编号
	 */
	private String externalSN;
	/**
	 * 供应商ID
	 */
	private String supplierId;
	/**
	 * 品牌
	 */
	private String brandName;
	/**
	 * 品牌ID
	 */
	private String brandId;
	/**
	 * 年份
	 */
	private String year;
	/**
	 * 季节
	 */
	private String season;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 大类
	 */
	private String topCategory;
	/**
	 * 小类
	 */
	private String subCategory;
	/**
	 * 吊牌价
	 */
	private BigDecimal tagPrice;

	public String getInernalSN() {
		return inernalSN;
	}

	public void setInernalSN(String inernalSN) {
		this.inernalSN = inernalSN;
	}

	public String getExternalSN() {
		return externalSN;
	}

	public void setExternalSN(String externalSN) {
		this.externalSN = externalSN;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTopCategory() {
		return topCategory;
	}

	public void setTopCategory(String topCategory) {
		this.topCategory = topCategory;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public BigDecimal getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(BigDecimal tagPrice) {
		this.tagPrice = tagPrice;
	}

	protected String getDisplayName() {
		return "门店商品信息";
	}

}

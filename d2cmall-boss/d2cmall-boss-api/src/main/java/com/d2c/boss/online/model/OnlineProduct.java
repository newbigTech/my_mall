package com.d2c.boss.online.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author xuhua
 * 
 */
@Entity()
@Table(name = "online_product")
public class OnlineProduct extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 图片地址
	 */
	private String picture;
	/**
	 * D2C货号
	 */
	private String inernalSn;
	/**
	 * 设计师货号
	 */
	@JsonIgnoreProperties
	private String externalSn;
	/**
	 * 原厂编号
	 */
	@JsonIgnoreProperties
	private String originalSn;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 副标题
	 */
	private String subTitle;
	/**
	 * 供应商
	 */
	private Long supplierId;
	/**
	 * 设计师ID
	 */
	private String designerId;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 大类
	 */
	private String topcategoryId;
	/**
	 * 小类
	 */
	private String subcategoryId;
	/**
	 * 执行标准
	 */
	private String exestandardId_;
	/**
	 * 发货说明
	 */
	private String sendRemark;
	/**
	 * 官网商品上下架状态， 0下架1上架
	 */
	private Integer d2cState;
	/**
	 * 微信商品上下架状态， 0下架1上架
	 */
	private Integer wgState;
	/**
	 * 年份
	 */
	private Integer year;
	/**
	 * 季节
	 */
	private Integer season;
	/**
	 * 吊牌价
	 */
	private BigDecimal tagPrice;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 购买方式
	 */
	private String buyWay;
	/**
	 * 同步时间戳
	 */
	private Date syndate;
	/**
	 * 对应原有的Id
	 */
	private String sourceId;
	/**
	 * 品牌名
	 */
	private String brandName;
	/**
	 * 最低价
	 */
	private BigDecimal minPrice;
	/**
	 * 最高价
	 */
	private BigDecimal maxPrice;
	/**
	 * 商品类别
	 */
	private String category;

	private Integer status;

	public String getSendRemark() {
		return sendRemark;
	}

	public void setSendRemark(String sendRemark) {
		this.sendRemark = sendRemark;
	}

	public String getInernalSn() {
		return inernalSn;
	}

	public void setInernalSn(String inernalSN) {
		this.inernalSn = inernalSN;
	}

	public String getExternalSn() {
		return externalSn;
	}

	public void setExternalSn(String externalSN) {
		this.externalSn = externalSN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public BigDecimal getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(BigDecimal shopPrice) {
		this.tagPrice = shopPrice;
	}

	public String getBuyWay() {
		return buyWay;
	}

	public void setBuyWay(String buyWay) {
		this.buyWay = buyWay;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getD2cState() {
		return d2cState;
	}

	public void setD2cState(Integer d2cState) {
		this.d2cState = d2cState;
	}

	public Integer getWgState() {
		return wgState;
	}

	public void setWgState(Integer wgState) {
		this.wgState = wgState;
	}

	public String getDesignerId() {
		return designerId;
	}

	public void setDesignerId(String designerId) {
		this.designerId = designerId;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOriginalSn() {
		return originalSn;
	}

	public void setOriginalSn(String originalSn) {
		this.originalSn = originalSn;
	}

	public Date getSyndate() {
		return syndate;
	}

	public void setSyndate(Date syndate) {
		this.syndate = syndate;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTopcategoryId() {
		return topcategoryId;
	}

	public void setTopcategoryId(String topcategoryId) {
		this.topcategoryId = topcategoryId;
	}

	public String getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(String subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public String getExestandardId_() {
		return exestandardId_;
	}

	public void setExestandardId_(String exestandardId_) {
		this.exestandardId_ = exestandardId_;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	protected String getDisplayName() {
		return "产品";
	}
	
}

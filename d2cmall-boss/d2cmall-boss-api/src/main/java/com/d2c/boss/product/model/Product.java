package com.d2c.boss.product.model;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 商品
 */
@Table(name = "crm_product")
public class Product extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品款号
	 */
	private String sn;
	/**
	 * 线上商品id
	 */
	private Long onlineId;
	/**
	 * 设计师款号
	 */
	private String externalSn;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品标签
	 */
	private String tags;
	/**
	 * 商品图片地址
	 */
	private String img;
	/**
	 * 商品描述
	 */
	private String remark;
	/**
	 * 吊牌价
	 */
	private BigDecimal originalPrice;
	// /**
	// * 销售价格
	// */
	// private BigDecimal salePrice;

	/**
	 * 三级分类
	 */
	private String leafCategory;
	/**
	 * 品牌编号
	 */
	private String brandCode;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 店铺编号
	 */
	private String shopCode;
	/**
	 * 店铺名称
	 */
	private String shopName;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	// public BigDecimal getSalePrice() {
	// return salePrice;
	// }
	//
	// public void setSalePrice(BigDecimal salePrice) {
	// this.salePrice = salePrice;
	// }

	public String getLeafCategory() {
		return leafCategory;
	}

	public void setLeafCategory(String leafCategory) {
		this.leafCategory = leafCategory;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getExternalSn() {
		return externalSn;
	}

	public void setExternalSn(String externalSn) {
		this.externalSn = externalSn;
	}

	public Long getOnlineId() {
		return onlineId;
	}

	public void setOnlineId(Long onlineId) {
		this.onlineId = onlineId;
	}

	public String getProductImgUrl() {
		if (this.getImg() != null) {
			return "http://img.d2c.cn/" + this.getImg();
		}
		return "";
	}
}

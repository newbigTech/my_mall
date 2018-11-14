package com.d2c.boss.order.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductReturnedDetailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 店铺号
	 */
	public String storeSn;
	/**
	 * 商品图片
	 */
	public String picture;
	/**
	 * 设计师货号
	 */
	public String externalSn;
	/**
	 * 颜色
	 */
	public String color;
	/**
	 * 尺码
	 */
	public String size;
	/**
	 * 单据时间
	 */
	private Date billDate;
	/**
	 * 每个SKU销量
	 */
	public Integer quantity;
	/**
	 * 大类
	 */
	public String topCategory;
	/**
	 * 退货原因
	 */
	private String remark;

	public ProductReturnedDetailDto(String storeSn, String picture, String externalSn, String color, String size,
			Date billDate, Integer quantity, String topCategory, String remark) {
		this.storeSn = storeSn;
		this.picture = picture;
		this.externalSn = externalSn;
		this.color = color;
		this.size = size;
		this.billDate = billDate;
		this.quantity = quantity;
		this.topCategory = topCategory;
		this.remark = remark;
	}

	public String getStoreSn() {
		return storeSn;
	}

	public void setStoreSn(String storeSn) {
		this.storeSn = storeSn;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getExternalSn() {
		return externalSn;
	}

	public void setExternalSn(String externalSn) {
		this.externalSn = externalSn;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getTopCategory() {
		return topCategory;
	}

	public void setTopCategory(String topCategory) {
		this.topCategory = topCategory;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

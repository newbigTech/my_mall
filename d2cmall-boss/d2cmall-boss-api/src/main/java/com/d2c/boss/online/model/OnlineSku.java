package com.d2c.boss.online.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

@Entity()
@Table(name = "td_online_sku")
public class OnlineSku extends PreUserDO {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private OnlineProduct productTemp;
	/**
	 * 条码
	 */
	private String barCode;
	/**
	 * 商品货号
	 */
	private String inernalSn;
	/**
	 * 商品数量(同步官网与微信，库存为store+adjustStore)
	 */
	private Integer store;
	/**
	 * 调整库存（+1）
	 */
	private Integer adjustStore = 0;
	/**
	 * 吊牌价
	 */
	private BigDecimal tagPrice;
	/**
	 * 官网销售价格
	 */
	private BigDecimal shopPrice;
	/***
	 * 微信销售价
	 */
	private BigDecimal wgShopPrice;
	/**
	 * 销售属性1（颜色）
	 */
	private String sale1;
	/**
	 * 销售属性2（尺码）
	 */
	private String sale2;
	/**
	 * 属性类型
	 */
	private String saleCategory;
	/**
	 * 同步时间戳
	 */
	private Date syndate;
	/**
	 * 状态
	 */
	private Integer status;

	public OnlineProduct getProductTemp() {
		return productTemp;
	}

	public void setProductTemp(OnlineProduct productTemp) {
		this.productTemp = productTemp;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barcode) {
		this.barCode = barcode;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public BigDecimal getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Integer getAdjustStore() {
		return adjustStore;
	}

	public void setAdjustStore(Integer adjustStore) {
		this.adjustStore = adjustStore;
	}

	public String getSale1() {
		return sale1;
	}

	public void setSale1(String sale1) {
		this.sale1 = sale1;
	}

	public BigDecimal getTagPrice() {
		return tagPrice;
	}

	public void setTagPrice(BigDecimal tagPrice) {
		this.tagPrice = tagPrice;
	}

	public String getSale2() {
		return sale2;
	}

	public void setSale2(String sale2) {
		this.sale2 = sale2;
	}

	public BigDecimal getWgShopPrice() {
		return wgShopPrice;
	}

	public void setWgShopPrice(BigDecimal wgShopPrice) {
		this.wgShopPrice = wgShopPrice;
	}

	public String getSaleCategory() {
		return saleCategory;
	}

	public void setSaleCategory(String saleCategory) {
		this.saleCategory = saleCategory;
	}

	public String getInernalSn() {
		return inernalSn;
	}

	public void setInernalSn(String inernalSn) {
		this.inernalSn = inernalSn;
	}

	public Date getSyndate() {
		return syndate;
	}

	public void setSyndate(Date syndate) {
		this.syndate = syndate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	protected String getDisplayName() {
		return "商品的SKU";
	}

}

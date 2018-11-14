package com.d2c.boss.online.model;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 订单明细
 */
@Table(name = "online_orderitem")
public class OnlineOrderItem extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单编号
	 */
	private String orderSn;

	/**
	 * 商品货号
	 */
	private String productSn;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品图片地址
	 */
	private String productImg;

	/**
	 * 三级分类
	 */
	private String leafCategory;
	/**
	 * 商品数量
	 */
	protected int quantity;
	/**
	 * 吊牌价格
	 */
	private BigDecimal originalPrice;
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;
	/**
	 * 优惠券分解金额
	 */
	private BigDecimal couponAmount = new BigDecimal(0);
	/**
	 * 订单满减等优惠分解金额
	 */
	private BigDecimal orderPromotionAmount = new BigDecimal(0);
	/**
	 * 销售属性1（颜色）
	 */
	private String sale1;
	/**
	 * 销售属性2（尺码）
	 */
	private String sale2;
	/**
	 * SKU编码
	 */
	private String productSkuSn;
	/**
	 * -3平台取消 -2交易关闭 -1用户取消 0待付款 1待发货 2已发货，待收货 3待门店接收 4已接收，待发货 6已锁定 7已签收 8交易成功
	 * 
	 */
	private int status;
	/**
	 * 品牌编码
	 */
	private String brandCode;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 店铺code
	 */
	private String shopCode;
	/**
	 * 店铺名称
	 */
	private String shopName;
	/**
	 * 商品总计优惠金额
	 */
	private BigDecimal promotionAmount;

	private int upStatus;

	/**
	 * 线上数据对应的id
	 */
	private BigDecimal sourceId;

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getLeafCategory() {
		return leafCategory;
	}

	public void setLeafCategory(String leafCategory) {
		this.leafCategory = leafCategory;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getSale1() {
		return sale1;
	}

	public void setSale1(String sale1) {
		this.sale1 = sale1;
	}

	public String getSale2() {
		return sale2;
	}

	public void setSale2(String sale2) {
		this.sale2 = sale2;
	}

	public String getProductSkuSn() {
		return productSkuSn;
	}

	public void setProductSkuSn(String productSkuSn) {
		this.productSkuSn = productSkuSn;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
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

	public BigDecimal getPromotionAmount() {
		return promotionAmount;
	}

	public void setPromotionAmount(BigDecimal promotionAmount) {
		this.promotionAmount = promotionAmount;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public int getUpStatus() {
		return upStatus;
	}

	public void setUpStatus(int upStatus) {
		this.upStatus = upStatus;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getOrderPromotionAmount() {
		return orderPromotionAmount;
	}

	public void setOrderPromotionAmount(BigDecimal orderPromotionAmount) {
		this.orderPromotionAmount = orderPromotionAmount;
	}

	public BigDecimal getSourceId() {
		return sourceId;
	}

	public void setSourceId(BigDecimal sourceId) {
		this.sourceId = sourceId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

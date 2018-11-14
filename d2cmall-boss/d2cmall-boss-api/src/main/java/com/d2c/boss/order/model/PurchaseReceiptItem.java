package com.d2c.boss.order.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 
 * @see 入库通知单明细 TODO
 *
 */
@Table(name = "scm_purchase_receiptitem")
public class PurchaseReceiptItem extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 收货店铺
	 */
	private String shopCode;
	/**
	 * 订单编号
	 */
	private String orderSn;
	/**
	 * 单据日期
	 */
	private Date billDate;
	/**
	 * 商品款号
	 */
	private String productSn;
	/**
	 * 品牌编号
	 */
	private String brandCode;
	/**
	 * SKU编码,条码
	 */
	private String productSkuSn;
	/**
	 * 销售属性1（颜色）
	 */
	private String sale1;
	/**
	 * 销售属性2（尺码）
	 */
	private String sale2;
	/**
	 * 通知数量
	 */
	private int quantity = 0;
	/**
	 * 入库数量
	 */
	private int factQuantity = 0;
	/**
	 * 单价
	 */
	private BigDecimal price = new BigDecimal(0);
	/**
	 * 组织编号
	 */
	private String orgId;

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getProductSkuSn() {
		return productSkuSn;
	}

	public void setProductSkuSn(String productSkuSn) {
		this.productSkuSn = productSkuSn;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getFactQuantity() {
		return factQuantity;
	}

	public void setFactQuantity(int factQuantity) {
		this.factQuantity = factQuantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}

package com.d2c.boss.online.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

@Entity()
@Table(name = "td_online_sales_orderitem")
public class OnlineSalesOrderItem extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 明细店铺
	 */
	private String storeSn;
	/**
	 * 明细主订单
	 */
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OnlineSalesOrder order;
	/**
	 * 明细编号
	 */
	@Column(name = "source_id")
	private String sourceId;
	/**
	 * 明细状态
	 */
	private String itemStatus;
	/**
	 * 订单编号
	 */
	private String orderSn;
	/**
	 * 商品款号
	 */
	private String productSn;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 设计师品牌
	 */
	private String designerName;
	/**
	 * SKU编码,条码
	 */
	private String skuSn;
	/**
	 * 属性类型
	 */
	private String saleCategory;
	/**
	 * 数量
	 */
	private Integer quantity = 0;
	/**
	 * 单价
	 */
	@Column(precision = 20, scale = 2)
	private BigDecimal price = new BigDecimal(0);
	/**
	 * 优惠金额
	 */
	private BigDecimal pomotionPrice;
	/**
	 * 小计：数量*单价-优惠金额=订单总金额+满减
	 */
	@Column(precision = 20, scale = 2)
	private BigDecimal subTotal;
	/**
	 * 备注
	 */
	private String itemMemo;
	/**
	 * 物流公司名称
	 */
	private String logisticsName;
	/**
	 * 物流编号
	 */
	private String logisticsSn;
	/**
	 * 退款单号
	 */
	private Long refundId;
	/**
	 * 退货单号
	 */
	private Long reshipId;
	/**
	 * 同步时间戳
	 */
	private Date syndate;
	/**
	 * 状态
	 */
	private Integer status;

	public String getStoreSn() {
		return storeSn;
	}

	public void setStoreSn(String storeSn) {
		this.storeSn = storeSn;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

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

	public String getSkuSn() {
		return skuSn;
	}

	public void setSkuSn(String skuSn) {
		this.skuSn = skuSn;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getPomotionPrice() {
		return pomotionPrice;
	}

	public void setPomotionPrice(BigDecimal pomotionPrice) {
		this.pomotionPrice = pomotionPrice;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public String getLogisticsSn() {
		return logisticsSn;
	}

	public void setLogisticsSn(String logisticsSn) {
		this.logisticsSn = logisticsSn;
	}

	public OnlineSalesOrder getOrder() {
		return order;
	}

	public void setOrder(OnlineSalesOrder order) {
		this.order = order;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public String getSaleCategory() {
		return saleCategory;
	}

	public void setSaleCategory(String saleCategory) {
		this.saleCategory = saleCategory;
	}

	public String getItemMemo() {
		return itemMemo;
	}

	public void setItemMemo(String itemMemo) {
		this.itemMemo = itemMemo;
	}

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public Long getReshipId() {
		return reshipId;
	}

	public void setReshipId(Long reshipId) {
		this.reshipId = reshipId;
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
		return "订单明细";
	}

}

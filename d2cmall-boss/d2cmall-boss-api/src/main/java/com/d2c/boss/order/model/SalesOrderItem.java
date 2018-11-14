package com.d2c.boss.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

@Entity()
@Table(name = "scm_sales_orderitem")
public class SalesOrderItem extends PreUserDO {

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
	private SalesOrder order;
	/**
	 * 单据日期
	 */
	private Date billDate;
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
	 * 供应商Id
	 */
	private String supplierId;
	/**
	 * 品牌Id
	 */
	private String brandId;
	/**
	 * SKU编码,条码
	 */
	private String skuSn;
	/**
	 * 销售属性1（颜色）
	 */
	private String color;
	/**
	 * 销售属性2（尺码）
	 */
	private String size;
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
	 * 组织编号
	 */
	private String orgId;
	/**
	 * 退货状态 2退货
	 */
	private Integer returnType;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否可见
	 */
	private Integer visible = 1;

	/**
	 * 
	 * CLOSE（已关闭订单），MALLCLOSE（用户关闭），AFTERCLOSE（退款退货完成订单），
	 * NORMAL（正常订单），DELIVERED（已发货订单），SUCCESS（已收货订单）
	 *
	 */
	public enum ItemStatus {
		AFTERCLOSE(-3), MALLCLOSE(-2), CLOSE(-1), NORMAL(1), DELIVERED(3), SUCCESS(8);
		private int code;
		private static Map<Integer, ItemStatus> holder = new HashMap<Integer, ItemStatus>();
		static {
			holder.put(-3, ItemStatus.AFTERCLOSE);
			holder.put(-2, ItemStatus.MALLCLOSE);
			holder.put(-1, ItemStatus.CLOSE);
			holder.put(1, ItemStatus.NORMAL);
			holder.put(3, ItemStatus.DELIVERED);
			holder.put(8, ItemStatus.SUCCESS);
		}

		ItemStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public static ItemStatus getStatus(int i) {
			return holder.get(i);
		}
	}

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

	public SalesOrder getOrder() {
		return order;
	}

	public void setOrder(SalesOrder order) {
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

	public String getColor() {
		return color;
	}

	public void setColor(String sale1) {
		this.color = sale1;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String sale2) {
		this.size = sale2;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public Integer getReturnType() {
		return returnType;
	}

	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	protected String getDisplayName() {
		return "订单明细";
	}

}

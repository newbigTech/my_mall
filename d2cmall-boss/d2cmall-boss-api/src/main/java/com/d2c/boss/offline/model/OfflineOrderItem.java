package com.d2c.boss.offline.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

@Entity()
@Table(name = "offline_orderitem")
public class OfflineOrderItem extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单编号
	 */
	private String orderSn;
	/**
	 * 商品编号
	 */
	private String productSn;
	/**
	 * sku编号
	 */
	private String skuSn;
	/**
	 * 数量
	 */
	private Integer quantity;
	/**
	 * 销售金额
	 */
	private BigDecimal amount;
	/**
	 * 实付金额
	 */
	private BigDecimal aAmount;
	/**
	 * 优惠金额
	 */
	private BigDecimal pAmount;
	/**
	 * 明细店铺
	 */
	private String storeSn;
	/**
	 * 设计师品牌
	 */
	private String designerName;
	/**
	 * 出库数量
	 */
	private Integer outQuantity;
	/**
	 * 类型 1 零售单 2 预订单
	 */
	private Integer itemType;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否可用（只有预定单同步作废项）
	 */
	private String isActive;
	/**
	 * 退货状态 2退货
	 */
	private Integer returnType;

	/**
	 * -3平台取消 -2交易关闭 -1用户取消 0待付款 1待发货 2已发货，待收货 3待门店接收 4已接收，待发货 6已锁定 7已签收 8交易成功
	 * 
	 */
	private int status;

	public String getStoreSn() {
		return storeSn;
	}

	public void setStoreSn(String storeSn) {
		this.storeSn = storeSn;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getpAmount() {
		return pAmount;
	}

	public void setpAmount(BigDecimal pAmount) {
		this.pAmount = pAmount;
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

	public BigDecimal getaAmount() {
		return aAmount;
	}

	public void setaAmount(BigDecimal aAmount) {
		this.aAmount = aAmount;
	}

	public Integer getOutQuantity() {
		return outQuantity;
	}

	public void setOutQuantity(Integer outQuantity) {
		this.outQuantity = outQuantity;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Integer getReturnType() {
		return returnType;
	}

	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	protected String getDisplayName() {
		return "零售明细";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

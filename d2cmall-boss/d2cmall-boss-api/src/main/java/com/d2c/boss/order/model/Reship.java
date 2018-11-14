package com.d2c.boss.order.model;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 退货单
 */
@Table(name = "crm_reship")
public class Reship extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 退货编号
	 */
	private String sn;
	/**
	 * 会员账号
	 */
	private String loginCode;
	/**
	 * 实际交易金额
	 */
	private BigDecimal tradeAmount;
	/**
	 * 退货状态：-3商家拒绝，-2用户取消， -1已关闭， 0正在申请， 1待仓库收货， 2客服同意， 8确认收货
	 */
	private int status;
	/**
	 * 对应订单编号
	 */
	private String orderSn;
	/**
	 * 退货理由
	 */
	private String reason;
	/**
	 * 物流编号
	 */
	private String deliverySn;
	/**
	 * 物流公司名称
	 */
	private String deliveryComp;
	/**
	 * 数量
	 */
	private int quantity;
	/**
	 * 商品货号
	 */
	private String productSn;
	/**
	 * 条码
	 */
	private String productSkuSn;
	/**
	 * 申请金额
	 */
	private BigDecimal applyAmount = new BigDecimal("0");
	/**
	 * 审核金额
	 */
	private BigDecimal totalAmount = new BigDecimal("0");
	/**
	 * 退款金额（现金，支付包或银行）
	 */
	private BigDecimal payAmount;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 品牌Code
	 */
	private String brandCode;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDeliverySn() {
		return deliverySn;
	}

	public void setDeliverySn(String deliverySn) {
		this.deliverySn = deliverySn;
	}

	public String getDeliveryComp() {
		return deliveryComp;
	}

	public void setDeliveryComp(String deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
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

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

}

package com.d2c.boss.order.model;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 换货单
 */
@Table(name = "crm_exchange")
public class Exchange extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 换货编号
	 */
	private String sn;
	/**
	 * 会员账号
	 */
	private String loginCode;
	/**
	 * 换货状态 -3商家拒绝 -2用户取消 -1已关闭 0正在申请 1客服同意 2待仓库收货 3待客服发货 4已发货 8换货结束
	 */
	private int status;
	/**
	 * 对应订单编号
	 */
	private String orderSn;
	/**
	 * 换货理由
	 */
	private String reason;
	/**
	 * 换货说明
	 */
	private String memo;
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
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 品牌Code
	 */
	private String brandCode;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

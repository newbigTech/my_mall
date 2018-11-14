package com.d2c.boss.order.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 退款单
 */
@Table(name = "crm_refund")
public class Refund extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 退款编号
	 */
	private String sn;
	/**
	 * 会员账号
	 */
	private String loginCode;
	/**
	 * 实际交易金额
	 */
	private BigDecimal tradeAmount = new BigDecimal("0");
	/**
	 * 退款状态： -1、 "商家拒绝退款"， -2、 "用户取消退款"， 0、 "正在申请退款"， 1、"客服正在确认"， 4、 "财务正在退款"，
	 * 8、"退款成功"
	 */
	private int status;
	/**
	 * 对应订单编号
	 */
	private String orderSn;
	/**
	 * 退款理由
	 */
	private String reason;
	/**
	 * 数量
	 */
	private int quantity;
	/**
	 * 售价
	 */
	private BigDecimal price;

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
	 * 退款时间
	 */
	private Date payDate;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}

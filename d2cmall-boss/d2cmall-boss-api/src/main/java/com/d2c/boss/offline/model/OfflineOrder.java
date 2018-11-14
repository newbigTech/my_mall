package com.d2c.boss.offline.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

@Entity()
@Table(name = "offline_order")
public class OfflineOrder extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 账号
	 */
	private String loginCode;
	/**
	 * 收货人姓名
	 */
	private String receiver;
	/**
	 * 收货人手机号
	 */
	private String contact;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区，县
	 */
	private String district;
	/**
	 * 收货地址
	 */
	private String address;
	/**
	 * 订单编号
	 */
	private String sn;
	/**
	 * 订单渠道：1、官网，2、ios，3、android， 4、wap，5、线下门店
	 */
	private String source;
	/**
	 * 门店code
	 */
	private String shopCode;
	/**
	 * 订单状态订单状态：-4交易关闭(付款以后用户退款成功，交易自动关闭)，-3平台取消，-2异常关闭(货到付款的关闭)，-1用户取消，0未处理，
	 * 1待付款，2待发货，3待发货，4已发货，8交易成功
	 */
	private int status;
	/**
	 * 实际商品应付金额=商品总价格-明细单折后金额-满减金额
	 * productTotalAmount-productPromotionAmount-orderPromotionAmount
	 */
	private BigDecimal totalAmount = new BigDecimal("0.00");
	/**
	 * 订单实付金额=商品总价格-明细单折后金额-满减金额-优惠券金额+运费
	 * productTotalAmount-productPromotionAmount-orderPromotionAmount-
	 * couponAmount+shippingRates
	 */
	private BigDecimal payAmount = new BigDecimal("0.00");
	/**
	 * 商品总价格 商品销售价格*数量，原始金额
	 */
	private BigDecimal productTotalAmount = new BigDecimal("0.00");
	/**
	 * 订单满减总计优惠金额
	 */
	private BigDecimal orderPromotionAmount = new BigDecimal("0.00");
	/**
	 * 商品明细总计优惠金额
	 */
	private BigDecimal productPromotionAmount = new BigDecimal("0.00");
	/**
	 * 优惠券使用金额
	 */
	private BigDecimal couponAmount = new BigDecimal(0);
	/**
	 * 运费
	 */
	private BigDecimal shippingRates = new BigDecimal(0);
	/**
	 * 物流编号
	 */
	private String deliverySn;
	/**
	 * 物流公司
	 */
	private String deliveryComp;
	/**
	 * 订单发货时间
	 */
	private Date deliveryTime;
	/**
	 * 订单付款时间
	 */
	private Date paymentTime;

	/**
	 * 订单完成时间
	 */
	private Date successTime;
	/**
	 * 是否使用优惠券：1、是，0、否
	 */
	private int couponed;
	/**
	 * 使用的优惠券编码
	 */
	private String couponInfo;

	/**
	 * 是否退货
	 */
	private String returnStatus;
	/**
	 * 是否冲红
	 */
	private String recopyStatus;
	/**
	 * 冲红日期
	 */
	private Date recopyDate;
	/**
	 * 是否可用（只有预定单同步作废项）
	 */
	private String isActive;
	/**
	 * 是否同步
	 */
	private int updateStatus;

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public BigDecimal getProductTotalAmount() {
		return productTotalAmount;
	}

	public void setProductTotalAmount(BigDecimal productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
	}

	public BigDecimal getOrderPromotionAmount() {
		return orderPromotionAmount;
	}

	public void setOrderPromotionAmount(BigDecimal orderPromotionAmount) {
		this.orderPromotionAmount = orderPromotionAmount;
	}

	public BigDecimal getProductPromotionAmount() {
		return productPromotionAmount;
	}

	public void setProductPromotionAmount(BigDecimal productPromotionAmount) {
		this.productPromotionAmount = productPromotionAmount;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getShippingRates() {
		return shippingRates;
	}

	public void setShippingRates(BigDecimal shippingRates) {
		this.shippingRates = shippingRates;
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

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public int getCouponed() {
		return couponed;
	}

	public void setCouponed(int couponed) {
		this.couponed = couponed;
	}

	public String getCouponInfo() {
		return couponInfo;
	}

	public void setCouponInfo(String couponInfo) {
		this.couponInfo = couponInfo;
	}

	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getRecopyStatus() {
		return recopyStatus;
	}

	public void setRecopyStatus(String recopyStatus) {
		this.recopyStatus = recopyStatus;
	}

	public Date getRecopyDate() {
		return recopyDate;
	}

	public void setRecopyDate(Date recopyDate) {
		this.recopyDate = recopyDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public int getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(int updateStatus) {
		this.updateStatus = updateStatus;
	}

}

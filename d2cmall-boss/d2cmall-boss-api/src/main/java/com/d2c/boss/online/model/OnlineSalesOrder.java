package com.d2c.boss.online.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

@Entity()
@Table(name = "td_online_sale_order")
public class OnlineSalesOrder extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单店铺
	 */
	private String storeSn;
	/**
	 * 订单编号
	 */
	@Column(unique = true)
	private String orderSn;
	/**
	 * 单据日期
	 */
	private Date billDate;
	/**
	 * 提交时间
	 */
	private Date submitDate;
	/**
	 * 订单状态
	 */
	private String orderStatus;
	/**
	 * 总金额(商品实际金额*数量-满减)
	 */
	@Column(precision = 20, scale = 2)
	private BigDecimal totalAmount;
	/**
	 * 优惠金额(满减)
	 */
	@Column(precision = 20, scale = 2)
	private BigDecimal pomotionAmount;
	/**
	 * 实付金额
	 */
	@Column(precision = 20, scale = 2)
	private BigDecimal paidAmount;
	/**
	 * 付款方式
	 */
	private String payType;
	/**
	 * 支付编号
	 */
	private String paymentSn;
	/**
	 * 下单会员编号
	 */
	private String memberSn;
	/**
	 * 真实姓名
	 */
	private String memberName;
	/**
	 * 用户备注
	 */
	private String memo;
	/**
	 * 收货人
	 */
	private String receiver;
	/**
	 * 联系方式
	 */
	private String contact;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区县
	 */
	private String district;
	/**
	 * 收货地址
	 */
	private String address;
	/**
	 * 客服备注
	 */
	private String remark;
	/**
	 * 导购来源（MEDIA，百度搜索，QQ等）
	 */
	private String guide;
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

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMemberSn() {
		return memberSn;
	}

	public void setMemberSn(String memberSn) {
		this.memberSn = memberSn;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPaymentSn() {
		return paymentSn;
	}

	public void setPaymentSn(String paymentSn) {
		this.paymentSn = paymentSn;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String reciver) {
		this.receiver = reciver;
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

	public BigDecimal getPomotionAmount() {
		return pomotionAmount;
	}

	public void setPomotionAmount(BigDecimal pomotionAmount) {
		this.pomotionAmount = pomotionAmount;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
		return "订单主信息";
	}

}

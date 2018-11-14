package com.d2c.boss.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

@Entity()
@Table(name = "scm_sale_order")
public class SalesOrder extends PreUserDO {

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
	 * 明细商品数量
	 */
	private int itemNum;
	/**
	 * 客服备注
	 */
	private String remark;
	/**
	 * 导购来源（MEDIA，百度搜索，QQ等）
	 */
	private String guide;
	/**
	 * 组织编号
	 */
	private String orgId;

	public enum OrderStatus {
		PAIDCLOSE(-2), CLOSE(-1), INITIAL(0), WAITINGFORPAY(1), WAITINGFORCONFIRM(2), WAITINGFORDELIVERY(3), DELIVERED(
				4), AfterSale(6), SUCCESS(8);
		private int code;
		private static Map<Integer, OrderStatus> holder = new HashMap<Integer, OrderStatus>();
		static {
			holder.put(-2, OrderStatus.PAIDCLOSE);
			holder.put(-1, OrderStatus.CLOSE);
			holder.put(0, OrderStatus.INITIAL);
			holder.put(1, OrderStatus.WAITINGFORPAY);
			holder.put(2, OrderStatus.WAITINGFORCONFIRM);
			holder.put(3, OrderStatus.WAITINGFORDELIVERY);
			holder.put(4, OrderStatus.DELIVERED);
			holder.put(6, OrderStatus.AfterSale);
			holder.put(8, OrderStatus.SUCCESS);
		}

		OrderStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public static OrderStatus getStatus(int i) {
			return holder.get(i);
		}
	};

	public String getStoreSn() {
		return storeSn;
	}

	public void setStoreSn(String storeSn) {
		this.storeSn = storeSn;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	protected String getDisplayName() {
		return "订单主信息";
	}
}

package com.d2c.order.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.d2c.common.api.query.model.BaseQuery;
import com.d2c.order.model.Order.OrderStatus;
import com.d2c.order.model.Order.OrderType;

public class OrderItemExportSearcher extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 订单创建时间(1昨天,2上周 ,3上个月,4上个季度,5上一个年度)
	 */
	private Integer createtimeType;

	/**
	 * 订单支付时间 (1昨天,2上周 ,3上个月,4上个季度,5上一个年度)
	 */
	private Integer paytimeType;

	/**
	 * 发货时间 (1昨天,2上周 ,3上个月,4上个季度,5上一个年度)
	 */
	private Integer deliverytimeType;

	/**
	 * a下单终端
	 */
	private String device;

	/**
	 * a会员ID
	 */
	private Long memberId;
	/**
	 * 用户loginCode
	 */
	private String loginCode;
	/**
	 * a设计师 ID(前端通过选择设计师品牌会传设计师id过来)
	 */
	private Long designerId;

	/**
	 * a订单状态
	 */
	private OrderStatus[] orderStatus = null;
	/**
	 * 订单状态名
	 */
	private String orderStatusName;

	/**
	 * 订单类型
	 */
	private OrderType[] orderType = null;

	/**
	 * a收货省份
	 */
	private String province;
	/**
	 * a收货城市
	 */
	private String city;

	/**
	 * a门店ID
	 */
	private Long storeId;

	/**
	 * a支付方式
	 */
	private Integer paymentType = null;
	/**
	 * a支付方式
	 */
	private Integer paymentCode;

	/**
	 * 明细排序字段
	 */
	private String itemOrderBy = "oe.create_date DESC";

	/**
	 * a运营小组
	 */
	private String operation;
	/**
	 * a结算状态
	 */
	private Integer balance;

	/**
	 * a创建开始时间
	 */
	private Date startDate;
	/**
	 * a创建结束时间
	 */
	private Date endDate;

	/**
	 * a支付开始时间
	 */
	private Date startPayDate;
	/**
	 * a支付结束时间
	 */
	private Date endPayDate;
	/**
	 * a发货时间 开始
	 */
	private Date deliveryTimeStart;
	/**
	 * a发货时间 结束
	 */
	private Date deliveryTimeEnd;

	/**
	 * aCRM接待小组
	 */
	private Long crmGroupId;

	/**
	 * a是否已开票
	 */
	private Integer invoiced;

	public Integer getCreatetimeType() {
		return createtimeType;
	}

	public void setCreatetimeType(Integer createtimeType) {
		this.createtimeType = createtimeType;
	}

	public Integer getPaytimeType() {
		return paytimeType;
	}

	public void setPaytimeType(Integer paytimeType) {
		this.paytimeType = paytimeType;
	}

	public Integer getDeliverytimeType() {
		return deliverytimeType;
	}

	public void setDeliverytimeType(Integer deliverytimeType) {
		this.deliverytimeType = deliverytimeType;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public Long getDesignerId() {
		return designerId;
	}

	public void setDesignerId(Long designerId) {
		this.designerId = designerId;
	}

	public OrderStatus[] getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus[] orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	/**
	 * 用于后台订单查询
	 * 
	 * @param orderStatusName
	 *            表单值
	 */
	public void setOrderStatusName(String orderStatusName) {
		if (StringUtils.isBlank(orderStatusName)) {
			return;
		}
		// 已付款,一般用于查询业绩
		if ("Payment".equals(orderStatusName)) {
			orderStatus = new OrderStatus[] { OrderStatus.WaitingForDelivery, OrderStatus.Delivered,
					OrderStatus.Success };
		} else {
			orderStatus = new OrderStatus[] { OrderStatus.valueOf(orderStatusName) };
		}
		this.orderStatusName = orderStatusName;
	}

	public OrderType[] getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType[] orderType) {
		this.orderType = orderType;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(Integer paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getItemOrderBy() {
		return itemOrderBy;
	}

	public void setItemOrderBy(String itemOrderBy) {
		this.itemOrderBy = itemOrderBy;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartPayDate() {
		return startPayDate;
	}

	public void setStartPayDate(Date startPayDate) {
		this.startPayDate = startPayDate;
	}

	public Date getEndPayDate() {
		return endPayDate;
	}

	public void setEndPayDate(Date endPayDate) {
		this.endPayDate = endPayDate;
	}

	public Date getDeliveryTimeStart() {
		return deliveryTimeStart;
	}

	public void setDeliveryTimeStart(Date deliveryTimeStart) {
		this.deliveryTimeStart = deliveryTimeStart;
	}

	public Date getDeliveryTimeEnd() {
		return deliveryTimeEnd;
	}

	public void setDeliveryTimeEnd(Date deliveryTimeEnd) {
		this.deliveryTimeEnd = deliveryTimeEnd;
	}

	public Long getCrmGroupId() {
		return crmGroupId;
	}

	public void setCrmGroupId(Long crmGroupId) {
		this.crmGroupId = crmGroupId;
	}

	public Integer getInvoiced() {
		return invoiced;
	}

	public void setInvoiced(Integer invoiced) {
		this.invoiced = invoiced;
	}

}

package com.d2c.order.model;

import javax.persistence.Table;

import com.d2c.common.api.annotation.AssertColumn;
import com.d2c.common.api.model.PreUserDO;
import com.d2c.order.support.LogisticsResource;

/**
 * 物流信息
 * 物流编号，物流公司和业务类型组成唯一键
 */
@Table(name = "o_logistics")
public class Logistics extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 物流单号
	 */
	@AssertColumn("物流单号不能为空")
	private String deliverySn;
	/**
	 * 物流信息
	 */
	private String deliveryInfo;
	/**
	 * 物流公司编号
	 */
	private String deliveryCode;
	/**
	 * 业务类型
	 */
	private String type;
	/**
	 * 状态 (0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单)
	 */
	private Integer status = 0;

	/**
	 * ORDER 订单，RESHIP 退款退货，EXCHANGE 换货(客户申请换货)，ALLOT 调拨， DELIVERY 换货(公司给客户发货)
	 */
	public enum BusinessType {
		ORDER, RESHIP, EXCHANGE, ALLOT, DELIVERY
	}

	public String getDeliverySn() {
		return deliverySn;
	}

	public void setDeliverySn(String deliverySn) {
		this.deliverySn = deliverySn;
	}

	public String getDeliveryInfo() {
		return deliveryInfo;
	}

	public void setDeliveryInfo(String deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public String getDeliveryCorpName() {
		return LogisticsResource.convertCode(deliveryCode);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return LogisticsResource.convertStatus(status);
	}

	public void setStatusName() {

	}

	public String getTel() {
		return LogisticsResource.convertToTel(deliveryCode);
	}

	public void setTel() {

	}

	public void createLogistics(String deliverySn, String com, String type, String creator) {
		this.setDeliverySn(deliverySn);
		this.setType(type);
		this.setDeliveryCode(com);
		this.setCreator(creator);
		this.setLastModifyMan(creator);
	}

}

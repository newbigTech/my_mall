package com.d2c.member.query;

import java.util.Date;

import com.d2c.common.api.query.model.BaseQuery;

public class ConsultSearcher extends BaseQuery {

	private static final long serialVersionUID = 1L;

	private Long productId;

	private Integer status;

	private Date startDate;

	private Date endDate;

	private String operation;
	/**
	 * D2C货号
	 */
	private String inernalSn;
	/**
	 * 设备终端
	 */
	private String device;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getInernalSn() {
		return inernalSn;
	}

	public void setInernalSn(String inernalSn) {
		this.inernalSn = inernalSn;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}

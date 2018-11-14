package com.d2c.boss.sys.model;

import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 接口日志
 */
@Table(name = "crm_api_log")
public class ApiLog extends PreUserDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 接口编码
	 */
	private String code;

	/**
	 * 接口名称
	 */
	private String name;

	/**
	 * 调用ip
	 */
	private String ip;

	/**
	 * 调用参数
	 */
	private String parameter;
	/**
	 * 调用开始时间
	 */
	private Date beginDate;

	/**
	 * 调用结束时间
	 */
	private Date endDate;

	/**
	 * 当前返回数据总数
	 */
	private Integer num;
	/**
	 * 是否成功
	 */
	private int status;

	/**
	 * 返回信息
	 */
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

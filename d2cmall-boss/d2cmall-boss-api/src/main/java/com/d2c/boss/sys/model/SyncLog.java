package com.d2c.boss.sys.model;

import java.sql.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 同步日志
 *
 */
@Table(name = "sys_sync_log")
public class SyncLog extends PreUserDO {

	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "success";
	public static final String FAILTURE = "failture";

	/**
	 * 类型
	 */
	private String type;
	/**
	 * 同步业务开始时间
	 */
	private Date buzBeginTime;
	/**
	 * 同步业务结束时间
	 */
	private Date buzEndTime;
	/**
	 * 同步开始时间
	 */
	private Date syncBeginTime;
	/**
	 * 同步结束时间
	 */
	private Date syncEndTime;
	/**
	 * 同步状态：success,failure
	 */
	private String state;
	/**
	 * 失败原因
	 */
	private String failReason;
	/**
	 * 同步间隔时间，秒
	 */
	private int interval = 60 * 60 * 24;
	/**
	 * 同步描述
	 */
	private String description;
	/**
	 * 状态
	 */
	private int status;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBuzBeginTime() {
		return buzBeginTime;
	}

	public void setBuzBeginTime(Date buzBeginTime) {
		this.buzBeginTime = buzBeginTime;
	}

	public Date getBuzEndTime() {
		return buzEndTime;
	}

	public void setBuzEndTime(Date buzEndTime) {
		this.buzEndTime = buzEndTime;
	}

	public Date getSyncBeginTime() {
		return syncBeginTime;
	}

	public void setSyncBeginTime(Date syncBeginTime) {
		this.syncBeginTime = syncBeginTime;
	}

	public Date getSyncEndTime() {
		return syncEndTime;
	}

	public void setSyncEndTime(Date syncEndTime) {
		this.syncEndTime = syncEndTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

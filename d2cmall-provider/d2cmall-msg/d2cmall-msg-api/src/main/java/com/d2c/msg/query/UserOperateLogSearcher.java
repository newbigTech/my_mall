package com.d2c.msg.query;

import java.util.Date;

import com.d2c.common.api.query.model.BaseQuery;
import com.d2c.msg.model.UserOperateLog.OperateLogType;

public class UserOperateLogSearcher extends BaseQuery {

	private static final long serialVersionUID = 1L;

	/**
	 * 类型
	 */
	private OperateLogType logType;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 业务ID
	 */
	private String objectId;

	public OperateLogType getLogType() {
		return logType;
	}

	public void setLogType(OperateLogType logType) {
		this.logType = logType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

}

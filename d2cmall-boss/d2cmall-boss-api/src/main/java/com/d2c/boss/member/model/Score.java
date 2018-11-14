package com.d2c.boss.member.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 积分活动 TODO
 */
@Table(name = "crm_score")
public class Score extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 会员账号
	 */
	private Long loginCode;
	/**
	 * 积分
	 */
	private int point;
	/**
	 * 方向
	 */
	private int direct = 1;
	/**
	 * 说明
	 */
	private String desc;
	/**
	 * 业务类型
	 */
	private String buzType;
	/**
	 * 业务ID
	 */
	private String buzId;
	/**
	 * 状态
	 */
	private int status;

	public Long getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(Long loginCode) {
		this.loginCode = loginCode;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBuzType() {
		return buzType;
	}

	public void setBuzType(String buzType) {
		this.buzType = buzType;
	}

	public String getBuzId() {
		return buzId;
	}

	public void setBuzId(String buzId) {
		this.buzId = buzId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}

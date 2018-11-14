package com.d2c.boss.member.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 积分活动策略 TODO
 */
@Table(name = "crm_score_strategy")
public class ScoreStrategy extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 标题说明
	 */
	private String title;
	/**
	 * 活动开始时间
	 */
	private Date beginDate;
	/**
	 * 活动结束时间
	 */
	private Date endDate;
	/**
	 * 会员等级
	 */
	private Long level;
	/**
	 * 默认积分系数
	 */
	private BigDecimal pointRatio = new BigDecimal(1);
	/**
	 * 应用到所有店仓
	 */
	private int all;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public BigDecimal getPointRatio() {
		return pointRatio;
	}

	public void setPointRatio(BigDecimal pointRatio) {
		this.pointRatio = pointRatio;
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

}

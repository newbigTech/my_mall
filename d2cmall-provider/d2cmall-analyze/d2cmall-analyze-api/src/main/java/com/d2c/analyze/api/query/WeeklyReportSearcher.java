package com.d2c.analyze.api.query;

import java.util.Date;

import com.d2c.common.api.query.model.BaseQuery;

public class WeeklyReportSearcher extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 开始时间
	 */
	private Date begainDate;
	/**
	 * 结束时间
	 */
	private Date endDate;

	public Date getBegainDate() {
		return begainDate;
	}

	public void setBegainDate(Date begainDate) {
		this.begainDate = begainDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}

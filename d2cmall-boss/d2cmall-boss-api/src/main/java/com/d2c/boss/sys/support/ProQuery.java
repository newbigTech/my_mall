package com.d2c.boss.sys.support;

import java.util.Date;

import com.d2c.common.api.query.model.BaseQuery;

public class ProQuery extends BaseQuery {

	private static final long serialVersionUID = 1L;

	Date startModifyTime;

	Date endModifyTime;

	public Date getStartModifyTime() {
		return startModifyTime;
	}

	public void setStartModifyTime(Date startModifyTime) {
		this.startModifyTime = startModifyTime;
	}

	public Date getEndModifyTime() {
		return endModifyTime;
	}

	public void setEndModifyTime(Date endModifyTime) {
		this.endModifyTime = endModifyTime;
	}

}

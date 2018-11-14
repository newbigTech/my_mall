package com.d2c.boss.order.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.order.model.Refund;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface RefundService {

	PageResult<Refund> findRefundsByQuery(PageModel page, ProQuery query);

	Refund insert(Refund refund);

	Refund findById(Long id);

	Date getLastSysDate();

	void save(List<Refund> refunds);

}

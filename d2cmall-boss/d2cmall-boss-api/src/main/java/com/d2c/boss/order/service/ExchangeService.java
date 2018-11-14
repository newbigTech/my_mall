package com.d2c.boss.order.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.order.model.Exchange;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface ExchangeService {

	PageResult<Exchange> findExchangesByQuery(PageModel page, ProQuery query);

	Exchange insert(Exchange refund);

	Exchange findById(Long id);

	Date getLastSysDate();

	void save(List<Exchange> exchanges);

}

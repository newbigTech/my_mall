package com.d2c.boss.order.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.order.model.Reship;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface ReshipService {

	PageResult<Reship> findReshipsByQuery(PageModel page, ProQuery query);

	Reship insert(Reship reship);

	Reship findById(Long id);

	Date getLastSysDate();

	void save(List<Reship> reships);

}

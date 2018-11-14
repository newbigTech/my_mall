package com.d2c.boss.member.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.member.model.Recharge;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface RechargeService {

	PageResult<Recharge> findRechargesByQuery(PageModel page, ProQuery query);

	Recharge insert(Recharge recharge);

	Date getLastSysDate();

	void save(List<Recharge> recharges);

	Recharge findById(Long id);
}

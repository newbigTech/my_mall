package com.d2c.boss.member.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.member.model.CouponDef;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface CouponDefService {

	PageResult<CouponDef> findCouponDefsByQuery(PageModel page, ProQuery query);

	CouponDef insert(CouponDef couponDef);

	Date getLastSysDate();

	void save(List<CouponDef> couponDefs);

	CouponDef findById(Long id);
}

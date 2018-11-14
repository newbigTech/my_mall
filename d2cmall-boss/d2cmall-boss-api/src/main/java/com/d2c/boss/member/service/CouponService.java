package com.d2c.boss.member.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.member.model.Coupon;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface CouponService {

	PageResult<Coupon> findCouponsByQuery(PageModel page, ProQuery query);

	Coupon insert(Coupon coupon);

	Date getLastSysDate();

	void save(List<Coupon> coupons);

	Coupon findById(Long id);
}

package com.d2c.boss.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.member.dao.CouponMapper;
import com.d2c.boss.member.model.Coupon;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("couponService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CouponServiceImpl extends ListServiceImpl<Coupon> implements CouponService {

	@Autowired
	private CouponMapper couponMapper;

	@Override
	public PageResult<Coupon> findCouponsByQuery(PageModel page, ProQuery query) {
		PageResult<Coupon> pager = new PageResult<Coupon>(page);
		int count = couponMapper.countCouponsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(couponMapper.findCouponsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Coupon insert(Coupon coupon) {
		Coupon result = this.save(coupon);
		return result;
	}

	@Override
	public Date getLastSysDate() {
		Date lastSysDate = couponMapper.getLastSysDate();
		return lastSysDate;
	}

	@Override
	public void save(List<Coupon> coupons) {
	}

	@Override
	public Coupon findById(Long id) {
		return this.findOneById(id);
	}

}

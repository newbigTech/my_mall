package com.d2c.boss.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.member.dao.CouponDefMapper;
import com.d2c.boss.member.model.CouponDef;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("couponDefService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CouponDefServiceImpl extends ListServiceImpl<CouponDef> implements CouponDefService {

	@Autowired
	private CouponDefMapper couponDefMapper;

	@Override
	public PageResult<CouponDef> findCouponDefsByQuery(PageModel page, ProQuery query) {
		PageResult<CouponDef> pager = new PageResult<CouponDef>(page);
		int count = couponDefMapper.countCouponDefsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(couponDefMapper.findCouponDefsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public CouponDef insert(CouponDef couponDef) {
		CouponDef result = this.save(couponDef);
		return result;
	}

	@Override
	public Date getLastSysDate() {
		Date lastSysDate = couponDefMapper.getLastSysDate();
		return lastSysDate;
	}

	@Override
	public void save(List<CouponDef> couponDefs) {
	}

	@Override
	public CouponDef findById(Long id) {
		return this.findOneById(id);
	}
}

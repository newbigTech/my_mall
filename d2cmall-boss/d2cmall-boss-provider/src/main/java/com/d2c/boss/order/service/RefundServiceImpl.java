package com.d2c.boss.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.order.dao.RefundMapper;
import com.d2c.boss.order.model.Refund;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("refundService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RefundServiceImpl extends ListServiceImpl<Refund> implements RefundService {
	@Autowired
	private RefundMapper refundMapper;

	@Override
	public PageResult<Refund> findRefundsByQuery(PageModel page, ProQuery query) {

		PageResult<Refund> pager = new PageResult<Refund>(page);
		int count = refundMapper.countRefundsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(refundMapper.findRefundsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Refund insert(Refund refund) {
		Refund result = this.save(refund);
		return result;
	}

	@Override
	public Refund findById(Long id) {
		return this.findOneById(id);
	}

	@Override
	public Date getLastSysDate() {
		Date lastSysDate = refundMapper.getLastSysDate();
		return lastSysDate;
	}

	@Override
	public void save(List<Refund> refunds) {
	}

}

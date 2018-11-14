package com.d2c.boss.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.member.dao.RechargeMapper;
import com.d2c.boss.member.model.Recharge;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("rechargeService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RechargeServiceImpl extends ListServiceImpl<Recharge> implements RechargeService {

	@Autowired
	private RechargeMapper rechargeMapper;

	@Override
	public PageResult<Recharge> findRechargesByQuery(PageModel page, ProQuery query) {
		PageResult<Recharge> pager = new PageResult<Recharge>(page);
		int count = rechargeMapper.countRechargesByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(rechargeMapper.findRechargesByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Recharge insert(Recharge recharge) {
		Recharge result = this.save(recharge);
		return result;
	}

	@Override
	public Date getLastSysDate() {
		return rechargeMapper.getLastSysDate();
	}

	@Override
	public void save(List<Recharge> recharges) {
	}

	@Override
	public Recharge findById(Long id) {
		return this.findOneById(id);
	}

}

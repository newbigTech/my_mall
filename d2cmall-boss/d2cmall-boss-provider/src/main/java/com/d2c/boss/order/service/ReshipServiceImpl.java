package com.d2c.boss.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.order.dao.ReshipMapper;
import com.d2c.boss.order.model.Reship;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("reshipService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ReshipServiceImpl extends ListServiceImpl<Reship> implements ReshipService {
	@Autowired
	private ReshipMapper reshipMapper;

	@Override
	public PageResult<Reship> findReshipsByQuery(PageModel page, ProQuery query) {

		PageResult<Reship> pager = new PageResult<Reship>(page);
		int count = reshipMapper.countReshipsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(reshipMapper.findReshipsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Reship insert(Reship reship) {
		Reship result = this.save(reship);
		return result;
	}

	@Override
	public Reship findById(Long id) {
		return this.findOneById(id);
	}

	@Override
	public Date getLastSysDate() {
		Date lastSysDate = reshipMapper.getLastSysDate();
		return lastSysDate;
	}

	@Override
	public void save(List<Reship> reships) {
	}

}

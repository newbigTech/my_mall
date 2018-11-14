package com.d2c.boss.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.order.dao.ExchangeMapper;
import com.d2c.boss.order.model.Exchange;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("exchangeService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ExchangeServiceImpl extends ListServiceImpl<Exchange> implements ExchangeService {
	@Autowired
	private ExchangeMapper exchangeMapper;

	@Override
	public PageResult<Exchange> findExchangesByQuery(PageModel page, ProQuery query) {

		PageResult<Exchange> pager = new PageResult<Exchange>(page);
		int count = exchangeMapper.countExchangesByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(exchangeMapper.findExchangesByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Exchange insert(Exchange exchange) {
		Exchange result = this.save(exchange);
		return result;
	}

	@Override
	public Exchange findById(Long id) {
		return this.findOneById(id);
	}

	@Override
	public Date getLastSysDate() {
		Date lastSysDate = exchangeMapper.getLastSysDate();
		return lastSysDate;
	}

	@Override
	public void save(List<Exchange> exchanges) {
	}

}

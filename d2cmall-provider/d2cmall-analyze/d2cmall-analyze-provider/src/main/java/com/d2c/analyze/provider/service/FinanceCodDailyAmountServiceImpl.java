package com.d2c.analyze.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.analyze.api.model.FinanceCodDailyAmount;
import com.d2c.analyze.api.query.FinanceDailyAmountSearcher;
import com.d2c.analyze.api.service.FinanceCodDailyAmountService;
import com.d2c.analyze.provider.dao.FinanceCodDailyAmountMapper;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service(protocol = "dubbo")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class FinanceCodDailyAmountServiceImpl extends ListServiceImpl<FinanceCodDailyAmount>
		implements FinanceCodDailyAmountService {

	@Autowired
	private FinanceCodDailyAmountMapper financeCodDailyAmountMapper;

	@Override
	public PageResult<FinanceCodDailyAmount> findCodBySearcher(FinanceDailyAmountSearcher searcher, PageModel page) {
		PageResult<FinanceCodDailyAmount> pager = new PageResult<FinanceCodDailyAmount>();
		int totalCount = financeCodDailyAmountMapper.countCodBySearcher(searcher);
		if (totalCount > 0) {
			List<FinanceCodDailyAmount> list = financeCodDailyAmountMapper.findCodBySearcher(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public FinanceCodDailyAmount findLastCod() {
		return financeCodDailyAmountMapper.findLastCod();
	}

	@Override
	public FinanceCodDailyAmount insert(FinanceCodDailyAmount financeCodDailyAmount) {
		return this.save(financeCodDailyAmount);
	}

	@Override
	public int countCodBySearcher(FinanceDailyAmountSearcher searcher) {
		return financeCodDailyAmountMapper.countCodBySearcher(searcher);
	}

}

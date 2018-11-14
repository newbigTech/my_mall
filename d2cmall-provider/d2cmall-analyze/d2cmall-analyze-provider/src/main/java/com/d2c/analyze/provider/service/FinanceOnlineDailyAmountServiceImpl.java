package com.d2c.analyze.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.analyze.api.model.FinanceOnlineDailyAmount;
import com.d2c.analyze.api.query.FinanceDailyAmountSearcher;
import com.d2c.analyze.api.service.FinanceOnlineDailyAmountService;
import com.d2c.analyze.provider.dao.FinanceOnlineDailyAmountMapper;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service(protocol = "dubbo")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class FinanceOnlineDailyAmountServiceImpl extends ListServiceImpl<FinanceOnlineDailyAmount>
		implements FinanceOnlineDailyAmountService {

	@Autowired
	private FinanceOnlineDailyAmountMapper financeOnlineDailyAmountMapper;

	@Override
	public PageResult<FinanceOnlineDailyAmount> findOnlineBySearcher(FinanceDailyAmountSearcher searcher,
			PageModel page) {
		PageResult<FinanceOnlineDailyAmount> pager = new PageResult<FinanceOnlineDailyAmount>(page);
		int totalCount = financeOnlineDailyAmountMapper.countOnlineBySearcher(searcher);
		if (totalCount > 0) {
			List<FinanceOnlineDailyAmount> list = financeOnlineDailyAmountMapper.findOnlineBySearcher(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public FinanceOnlineDailyAmount findLastOne() {
		return financeOnlineDailyAmountMapper.findLastOne();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public FinanceOnlineDailyAmount insert(FinanceOnlineDailyAmount financeOnlineDailyAmount) {
		return this.save(financeOnlineDailyAmount);
	}

	@Override
	public int countBySearcher(FinanceDailyAmountSearcher searcher) {
		return financeOnlineDailyAmountMapper.countOnlineBySearcher(searcher);
	}

}

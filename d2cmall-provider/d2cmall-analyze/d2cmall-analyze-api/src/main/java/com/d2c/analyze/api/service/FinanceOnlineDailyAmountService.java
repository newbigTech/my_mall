package com.d2c.analyze.api.service;

import com.d2c.analyze.api.model.FinanceOnlineDailyAmount;
import com.d2c.analyze.api.query.FinanceDailyAmountSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface FinanceOnlineDailyAmountService {

	FinanceOnlineDailyAmount insert(FinanceOnlineDailyAmount financeDailyAmount);

	int countBySearcher(FinanceDailyAmountSearcher searcher);

	FinanceOnlineDailyAmount findLastOne();

	PageResult<FinanceOnlineDailyAmount> findOnlineBySearcher(FinanceDailyAmountSearcher searcher, PageModel page);

}

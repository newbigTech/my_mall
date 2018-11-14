package com.d2c.analyze.api.service;

import com.d2c.analyze.api.model.FinanceCodDailyAmount;
import com.d2c.analyze.api.query.FinanceDailyAmountSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface FinanceCodDailyAmountService {

	PageResult<FinanceCodDailyAmount> findCodBySearcher(FinanceDailyAmountSearcher searcher, PageModel page);

	int countCodBySearcher(FinanceDailyAmountSearcher searcher);

	FinanceCodDailyAmount findLastCod();

	FinanceCodDailyAmount insert(FinanceCodDailyAmount financeCodDailyAmount);

}

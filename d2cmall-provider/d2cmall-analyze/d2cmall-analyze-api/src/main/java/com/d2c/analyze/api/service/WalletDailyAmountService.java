package com.d2c.analyze.api.service;

import com.d2c.analyze.api.model.WalletDailyAmount;
import com.d2c.analyze.api.query.FinanceDailyAmountSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface WalletDailyAmountService {

	WalletDailyAmount findLastOne();

	WalletDailyAmount insert(WalletDailyAmount walletAmount);

	PageResult<WalletDailyAmount> findWalletBySearcher(FinanceDailyAmountSearcher searcher, PageModel page);

	int countWalletBySearcher(FinanceDailyAmountSearcher searcher);

}

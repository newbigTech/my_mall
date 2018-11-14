package com.d2c.analyze.api.service;

import com.d2c.analyze.api.model.WalletSummary;
import com.d2c.analyze.api.query.WalletSummarySearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface WalletSummaryService {

	WalletSummary insert(WalletSummary walletSummary);

	PageResult<WalletSummary> findBySearcher(WalletSummarySearcher searcher, PageModel page);

	WalletSummary findLastOne();

	int countBySearcher(WalletSummarySearcher searcher);
}

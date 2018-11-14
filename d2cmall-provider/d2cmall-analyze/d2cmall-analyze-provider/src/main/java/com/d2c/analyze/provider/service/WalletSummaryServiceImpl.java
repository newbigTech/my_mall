package com.d2c.analyze.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.analyze.api.model.WalletSummary;
import com.d2c.analyze.api.query.WalletSummarySearcher;
import com.d2c.analyze.api.service.WalletSummaryService;
import com.d2c.analyze.provider.dao.WalletSummaryMapper;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service(protocol = "dubbo")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class WalletSummaryServiceImpl extends ListServiceImpl<WalletSummary> implements WalletSummaryService {

	@Autowired
	private WalletSummaryMapper walletSummaryMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public WalletSummary insert(WalletSummary financeWalletDailyAmount) {
		return this.save(financeWalletDailyAmount);
	}

	@Override
	public PageResult<WalletSummary> findBySearcher(WalletSummarySearcher searcher, PageModel page) {
		PageResult<WalletSummary> pager = new PageResult<WalletSummary>(page);
		int totalCount = walletSummaryMapper.countBySearcher(searcher);
		if (totalCount > 0) {
			List<WalletSummary> list = walletSummaryMapper.findBySearcher(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public WalletSummary findLastOne() {
		return walletSummaryMapper.findLastOne();
	}

	@Override
	public int countBySearcher(WalletSummarySearcher searcher) {
		return walletSummaryMapper.countBySearcher(searcher);
	}

}

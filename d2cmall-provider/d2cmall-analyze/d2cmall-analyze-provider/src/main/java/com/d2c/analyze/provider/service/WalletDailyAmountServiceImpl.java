package com.d2c.analyze.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.analyze.api.model.WalletDailyAmount;
import com.d2c.analyze.api.query.FinanceDailyAmountSearcher;
import com.d2c.analyze.api.service.WalletDailyAmountService;
import com.d2c.analyze.provider.dao.WalletDailyAmountMapper;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service(protocol = "dubbo")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class WalletDailyAmountServiceImpl extends ListServiceImpl<WalletDailyAmount>
		implements WalletDailyAmountService {

	@Autowired
	private WalletDailyAmountMapper walletDailyMapper;

	@Override
	public WalletDailyAmount findLastOne() {
		return walletDailyMapper.findLastOne();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public WalletDailyAmount insert(WalletDailyAmount walletAmount) {
		return this.save(walletAmount);
	}

	@Override
	public PageResult<WalletDailyAmount> findWalletBySearcher(FinanceDailyAmountSearcher searcher, PageModel page) {
		PageResult<WalletDailyAmount> pager = new PageResult<WalletDailyAmount>(page);
		int totalCount = walletDailyMapper.countBySearcher(searcher);
		if (totalCount > 0) {
			List<WalletDailyAmount> list = walletDailyMapper.findBySearcher(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public int countWalletBySearcher(FinanceDailyAmountSearcher searcher) {
		return walletDailyMapper.countBySearcher(searcher);
	}

}

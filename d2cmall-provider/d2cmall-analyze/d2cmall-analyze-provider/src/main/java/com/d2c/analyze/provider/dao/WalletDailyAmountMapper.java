package com.d2c.analyze.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.analyze.api.model.WalletDailyAmount;
import com.d2c.analyze.api.query.FinanceDailyAmountSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface WalletDailyAmountMapper extends SuperMapper<WalletDailyAmount> {

	WalletDailyAmount findLastOne();

	int countBySearcher(@Param("searcher") FinanceDailyAmountSearcher searcher);

	List<WalletDailyAmount> findBySearcher(@Param("searcher") FinanceDailyAmountSearcher searcher,
			@Param("page") PageModel page);

}

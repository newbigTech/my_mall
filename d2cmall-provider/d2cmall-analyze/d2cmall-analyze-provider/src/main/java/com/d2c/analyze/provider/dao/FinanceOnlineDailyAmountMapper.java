package com.d2c.analyze.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.analyze.api.model.FinanceOnlineDailyAmount;
import com.d2c.analyze.api.query.FinanceDailyAmountSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface FinanceOnlineDailyAmountMapper extends SuperMapper<FinanceOnlineDailyAmount> {

	int countOnlineBySearcher(@Param("searcher") FinanceDailyAmountSearcher searcher);

	List<FinanceOnlineDailyAmount> findOnlineBySearcher(@Param("searcher") FinanceDailyAmountSearcher searcher,
			@Param("page") PageModel page);

	FinanceOnlineDailyAmount findLastOne();
}

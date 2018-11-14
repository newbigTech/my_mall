package com.d2c.analyze.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.analyze.api.model.FinanceCodDailyAmount;
import com.d2c.analyze.api.query.FinanceDailyAmountSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface FinanceCodDailyAmountMapper extends SuperMapper<FinanceCodDailyAmount> {

	int countCodBySearcher(@Param("searcher") FinanceDailyAmountSearcher searcher);

	List<FinanceCodDailyAmount> findCodBySearcher(@Param("searcher") FinanceDailyAmountSearcher searcher,
			@Param("page") PageModel page);

	FinanceCodDailyAmount findLastCod();

}

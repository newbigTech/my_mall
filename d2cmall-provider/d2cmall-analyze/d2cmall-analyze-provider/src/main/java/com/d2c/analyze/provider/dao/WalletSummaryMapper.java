package com.d2c.analyze.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.analyze.api.model.WalletSummary;
import com.d2c.analyze.api.query.WalletSummarySearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface WalletSummaryMapper extends SuperMapper<WalletSummary> {

	int countBySearcher(@Param("searcher") WalletSummarySearcher searcher);

	List<WalletSummary> findBySearcher(@Param("searcher") WalletSummarySearcher searcher,
			@Param("page") PageModel page);

	WalletSummary findLastOne();

}

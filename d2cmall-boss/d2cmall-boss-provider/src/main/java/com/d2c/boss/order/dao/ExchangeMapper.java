package com.d2c.boss.order.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.order.model.Exchange;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ExchangeMapper extends SuperMapper<Exchange> {

	int countExchangesByQuery(@Param("query") ProQuery query);

	List<Exchange> findExchangesByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	Date getLastSysDate();
}

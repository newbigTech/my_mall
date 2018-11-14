package com.d2c.boss.order.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.order.model.Refund;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface RefundMapper extends SuperMapper<Refund> {

	int countRefundsByQuery(@Param("query") ProQuery query);

	List<Refund> findRefundsByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	Date getLastSysDate();
}

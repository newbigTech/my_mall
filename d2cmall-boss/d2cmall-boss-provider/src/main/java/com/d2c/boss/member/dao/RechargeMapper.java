package com.d2c.boss.member.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.member.model.Recharge;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface RechargeMapper extends SuperMapper<Recharge> {

	int countRechargesByQuery(@Param("query") ProQuery query);

	List<Recharge> findRechargesByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	Date getLastSysDate();
}

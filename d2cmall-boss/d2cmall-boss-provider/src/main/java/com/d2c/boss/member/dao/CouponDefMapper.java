package com.d2c.boss.member.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.member.model.CouponDef;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface CouponDefMapper extends SuperMapper<CouponDef> {

	int countCouponDefsByQuery(@Param("query") ProQuery query);

	List<CouponDef> findCouponDefsByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	Date getLastSysDate();
}

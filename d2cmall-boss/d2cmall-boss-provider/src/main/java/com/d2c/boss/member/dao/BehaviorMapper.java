package com.d2c.boss.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.member.model.Behavior;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface BehaviorMapper extends SuperMapper<Behavior> {

	int countBehaviorsByQuery(@Param("query") ProQuery query);

	List<Behavior> findBehaviorsByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

}

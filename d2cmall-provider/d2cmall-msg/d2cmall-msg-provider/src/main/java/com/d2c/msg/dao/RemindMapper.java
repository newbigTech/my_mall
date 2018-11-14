package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.Remind;
import com.d2c.msg.query.RemindSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface RemindMapper extends SuperMapper<Remind> {

	Remind findByMemberId(Long id);

	List<Remind> findBySearcher(@Param("searcher") RemindSearcher searcher, @Param("pager") PageModel pager);

	int countBySearcher(@Param("searcher") RemindSearcher searcher);

	List<Long> findSourceIdByType(@Param("type") String type, @Param("smsSend") Integer smsSend);

}
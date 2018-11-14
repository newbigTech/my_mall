package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.BurgeonErrorLog;
import com.d2c.msg.query.BurgeonErrorLogSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface BurgeonErrorLogMapper extends SuperMapper<BurgeonErrorLog> {

	List<BurgeonErrorLog> findBySearch(@Param("searcher") BurgeonErrorLogSearcher search,
			@Param("page") PageModel page);

	int countBySearch(@Param("searcher") BurgeonErrorLogSearcher search);

	int doHandle(@Param("id") Long id, @Param("handleMan") String handleMan,
			@Param("handleContent") String handleContent);

}

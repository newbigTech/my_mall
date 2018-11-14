package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.ErrorLog;
import com.d2c.msg.query.ErrorLogSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ErrorLogMapper extends SuperMapper<ErrorLog> {

	List<ErrorLog> findBySearcher(@Param("searcher") ErrorLogSearcher searcher, @Param("pager") PageModel pager);

	int countBySearcher(@Param("searcher") ErrorLogSearcher searcher);

}

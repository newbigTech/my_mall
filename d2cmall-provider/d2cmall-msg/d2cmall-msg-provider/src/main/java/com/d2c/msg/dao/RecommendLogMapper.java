package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.RecommendLog;
import com.d2c.msg.query.RecommendLogSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface RecommendLogMapper extends SuperMapper<RecommendLog> {

	List<RecommendLog> findBySearcher(@Param("searcher") RecommendLogSearcher searcher, @Param("page") PageModel page);

	int countBySearcher(@Param("searcher") RecommendLogSearcher searcher);

}

package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.VoteDef;
import com.d2c.cms.query.VoteDefSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface VoteDefMapper extends SuperMapper<VoteDef> {
	List<VoteDef> findBySearcher(@Param("searcher") VoteDefSearcher searcher, @Param("page") PageModel page);

	Integer countBySearcher(@Param("searcher") VoteDefSearcher searcher);

	int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}

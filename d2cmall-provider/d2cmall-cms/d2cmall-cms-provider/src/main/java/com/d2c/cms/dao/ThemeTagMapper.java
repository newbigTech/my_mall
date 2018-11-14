package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.ThemeTag;
import com.d2c.cms.query.ThemeTagSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ThemeTagMapper extends SuperMapper<ThemeTag> {

	List<ThemeTag> findBySearcher(@Param("searcher") ThemeTagSearcher searcher, @Param("page") PageModel page);

	Integer countBySearcher(@Param("searcher") ThemeTagSearcher searcher);

	ThemeTag findFixedOne();

	List<ThemeTag> findAll(@Param("type") String type);

	int updateSort(@Param("id") Long id, @Param("sort") Integer sort);

	int updateStatus(@Param("id") Long id, @Param("status") Integer status);

	int delete(Long id);

}

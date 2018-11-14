package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.AppNavigation;
import com.d2c.cms.query.AppNavigationSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface AppNavigationMapper extends SuperMapper<AppNavigation> {

	int updateStatus(@Param("id") Long id, @Param("status") Integer status, @Param("operator") String operator);

	List<AppNavigation> findAllEnable();

	int countBySearcher(@Param("searcher") AppNavigationSearcher searcher);

	List<AppNavigation> selectBySearcher(@Param("searcher") AppNavigationSearcher searcher,
			@Param("page") PageModel page);

}

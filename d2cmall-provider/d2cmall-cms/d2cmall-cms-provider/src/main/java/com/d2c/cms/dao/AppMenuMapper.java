package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.AppMenu;
import com.d2c.cms.query.AppMenuSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface AppMenuMapper extends SuperMapper<AppMenu> {

	int countBySearcher(@Param("searcher") AppMenuSearcher searcher);

	List<AppMenu> findBySearcher(@Param("searcher") AppMenuSearcher searcher, @Param("pager") PageModel pager);

	int updateStatusById(@Param("id") Long id, @Param("status") Integer status, @Param("name") String name);

	int updateSortById(@Param("id") Long id, @Param("sort") Integer sort);

	List<AppMenu> findByStatus(@Param("status") Integer status, @Param("version") String version);

}

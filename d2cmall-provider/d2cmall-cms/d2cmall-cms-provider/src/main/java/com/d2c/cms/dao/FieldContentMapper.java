package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.FieldContent;
import com.d2c.cms.query.FieldContentSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface FieldContentMapper extends SuperMapper<FieldContent> {

	List<FieldContent> findByGroupAndPage(@Param("group") String group, @Param("pageid") Long pageid,
			@Param("pager") PageModel pager);

	int countByGroupAndPage(@Param("group") String group, @Param("pageid") Long pageid);

	List<FieldContent> findBySearch(@Param("pager") PageModel pager, @Param("searcher") FieldContentSearcher searcher);

	int countBySearch(@Param("searcher") FieldContentSearcher searcher);
}
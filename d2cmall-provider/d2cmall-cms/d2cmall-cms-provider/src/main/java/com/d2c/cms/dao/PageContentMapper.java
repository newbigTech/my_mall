package com.d2c.cms.dao;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.PageContent;
import com.d2c.mybatis.mapper.SuperMapper;

public interface PageContentMapper extends SuperMapper<PageContent> {

	PageContent findOneByModule(@Param("pageDefId") Long pageDefId, @Param("status") int status);

	Long findIdByModule(@Param("pageDefId") Long pageDefId, @Param("status") int status);

	int updateStatusById(@Param("id") Long id, @Param("status") int status);

	PageContent findById(Long id);
	
	int delete(@Param(value = "historyId") Long historyId);
	
	
}
package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.Section;
import com.d2c.cms.query.SectionSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface SectionMapper extends SuperMapper<Section> {

	List<Section> findBySearcher(@Param("searcher") SectionSearcher searcher, @Param("pager") PageModel pager);

	int countBySearcher(@Param("searcher") SectionSearcher searcher);

	int deleteByModuleId(Long moduleId);
}

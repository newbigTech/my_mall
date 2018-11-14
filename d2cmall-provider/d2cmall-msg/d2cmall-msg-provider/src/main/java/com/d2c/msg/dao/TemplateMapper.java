package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.Template;
import com.d2c.msg.query.TemplateSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface TemplateMapper extends SuperMapper<Template> {

	List<Template> findBySearch(@Param("pager") PageModel pager, @Param("searcher") TemplateSearcher searcher);

	int countBySearch(@Param("searcher") TemplateSearcher searcher);

}

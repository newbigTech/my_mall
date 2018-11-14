package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.PageDefine;
import com.d2c.cms.model.PageDefine.MODULE;
import com.d2c.cms.model.PageDefine.TERMINAL;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface PageDefineMapper extends SuperMapper<PageDefine> {

	PageDefine findPageDefine(@Param("module") MODULE module, @Param("terminal") TERMINAL terminal,
			@Param("version") Integer version);

	List<PageDefine> findBySearch(@Param("searcher") PageDefine searcher, @Param("pager") PageModel pager);

	int countBySearch(@Param("searcher") PageDefine searcher);

	int update(PageDefine define);
}

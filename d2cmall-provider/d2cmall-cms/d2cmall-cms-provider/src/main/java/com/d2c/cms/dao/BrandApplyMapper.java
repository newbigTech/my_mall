package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.BrandApply;
import com.d2c.cms.query.BrandApplySearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface BrandApplyMapper extends SuperMapper<BrandApply> {

	BrandApply findByMemberId(@Param("memberId") Long memberId);

	List<BrandApply> findBySearcher(@Param("searcher") BrandApplySearcher searcher, @Param("pager") PageModel pager);

	int countBySearcher(@Param("searcher") BrandApplySearcher searcher);

}

package com.d2c.boss.product.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.product.model.BrandTags;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface BrandTagsMapper extends SuperMapper<BrandTags> {

	int countBrandTagsByQuery(@Param("query") ProQuery query);

	List<BrandTags> findBrandTagsByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	Date getLastSysDate();
}
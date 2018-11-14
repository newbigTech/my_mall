package com.d2c.boss.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.product.model.BrandCategory;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface BrandCategoryMapper extends SuperMapper<BrandCategory> {

	int countBrandCategorysByQuery(@Param("query") ProQuery query);

	List<BrandCategory> findBrandCategorysByQuery(@Param("pager") PageModel pager,
			@Param("query") ProQuery query);

}

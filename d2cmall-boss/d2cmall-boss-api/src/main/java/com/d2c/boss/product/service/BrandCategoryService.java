package com.d2c.boss.product.service;

import com.d2c.boss.product.model.BrandCategory;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface BrandCategoryService {

	PageResult<BrandCategory> findBrandCategorysByQuery(PageModel page, ProQuery query);

	BrandCategory insert(BrandCategory brandCategory);
}

package com.d2c.boss.product.service;

import java.util.List;

import com.d2c.boss.product.model.ProductCategory;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface ProductCategoryService {

	PageResult<ProductCategory> findProductCategorysByQuery(PageModel page, ProQuery query);

	ProductCategory insert(ProductCategory productCategory);

	void save(List<ProductCategory> productCategorys);

	void sendInfoToCrm();
}

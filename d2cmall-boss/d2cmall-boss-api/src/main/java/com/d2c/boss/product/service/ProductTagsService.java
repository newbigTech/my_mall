package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.product.model.ProductTags;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface ProductTagsService {

	PageResult<ProductTags> findProductTagsByQuery(PageModel page, ProQuery query);

	ProductTags insert(ProductTags productTags);

	ProductTags findById(Long id);

	Date getLastSysDate();

	void save(List<ProductTags> productTags);
}

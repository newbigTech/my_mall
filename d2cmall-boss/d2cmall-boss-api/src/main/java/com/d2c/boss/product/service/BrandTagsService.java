package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.product.model.BrandTags;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface BrandTagsService {

	PageResult<BrandTags> findBrandTagsByQuery(PageModel page, ProQuery query);

	BrandTags insert(BrandTags brandTags);

	BrandTags findById(Long id);

	Date getLastSysDate();

	void save(List<BrandTags> brands);

}

package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.product.model.Brand;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface BrandService {

	PageResult<Brand> findBrandsByQuery(PageModel page, ProQuery query);

	Brand insert(Brand brand);

	Brand findById(Long id);

	Date getLastSysDate();

	void save(List<Brand> brands);

	void insertOnlineBrand();

	void updateOnlineBrand();

	void insertOfflineBrand();

	void updateOfflineBrand();
}

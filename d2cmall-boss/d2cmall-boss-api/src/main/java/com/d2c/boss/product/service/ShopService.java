package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.product.model.Shop;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface ShopService {

	PageResult<Shop> findShopsByQuery(PageModel page, ProQuery query);

	Shop insert(Shop shop);

	Shop findById(Long id);

	Date getLastSysDate();

	void save(List<Shop> shops);

}

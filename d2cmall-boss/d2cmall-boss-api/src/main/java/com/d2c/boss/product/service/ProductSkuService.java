package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.product.model.ProductSku;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface ProductSkuService {

	PageResult<ProductSku> findProductSkusByQuery(PageModel page, ProQuery query);

	ProductSku insert(ProductSku productSku);

	List<ProductSku> findByProductSn(String productSn);

	ProductSku findById(Long id);

	Date getLastSysDate();

	void save(List<ProductSku> productSkus);

	// used
	void updateOnlineProductSku();

	void insertOnlineProductSku();

	void updateOfflineProductSku();

	void insertOfflineProductSku();

	void updateOnlineStatus();

	void updateOfflineStatus();
}

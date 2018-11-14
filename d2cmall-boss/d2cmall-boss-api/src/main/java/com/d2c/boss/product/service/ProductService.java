package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.product.dto.ProductDto;
import com.d2c.boss.product.model.Product;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface ProductService {

	PageResult<Product> findProductsByQuery(PageModel page, ProQuery query);

	Product insert(Product product);

	PageResult<ProductDto> findProductDtosByQuery(PageModel page, ProQuery query);

	Product findById(Long id);

	Date getLastSysDate();

	void save(List<Product> products);

	// used
	void updateOnlineProduct();

	void insertOnlineProduct();

	void updateOfflineProduct();

	void insertOfflineProduct();

	void updateOnlineStatus();

	void updateOfflineStatus();
}

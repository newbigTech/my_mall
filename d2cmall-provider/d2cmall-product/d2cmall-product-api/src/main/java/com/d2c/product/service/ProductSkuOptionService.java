package com.d2c.product.service;

import java.util.List;

import com.d2c.product.model.ProductSkuOption;

public interface ProductSkuOptionService {
	ProductSkuOption insert(ProductSkuOption productSkuOption);

	int update(ProductSkuOption productSkuOption);

	List<ProductSkuOption> findByProductId(Long productId);

	int updateMarkByProductId(Long productId, Integer mark);
}

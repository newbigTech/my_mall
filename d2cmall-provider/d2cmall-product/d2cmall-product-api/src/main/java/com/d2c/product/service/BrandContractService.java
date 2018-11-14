package com.d2c.product.service;

import java.util.List;

import com.d2c.product.model.BrandContract;

public interface BrandContractService {

	BrandContract insert(BrandContract brandContract);

	List<BrandContract> findByBrandId(Long brandId);

	int deleteById(Long id);

	int update(BrandContract brandContract);

	List<Long> findIdsByBrandId(Long brandId);

	BrandContract findByBrandIdAndType(Long brandId, String type);
}

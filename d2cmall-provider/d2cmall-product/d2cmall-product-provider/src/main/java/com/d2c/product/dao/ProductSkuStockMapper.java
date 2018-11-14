package com.d2c.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.product.model.ProductSkuStock;

public interface ProductSkuStockMapper extends SuperMapper<ProductSkuStock> {

	int deleteAll();

	int delete(@Param("storeCode") String storeCode, @Param("barCode") String barCode);

	int update(@Param("storeCode") String storeCode, @Param("barCode") String barCode, @Param("stock") int stock);

	ProductSkuStock findOne(@Param("storeCode") String storeCode, @Param("barCode") String barCode);

	List<ProductSkuStock> findBySkuAndStore(@Param("productSkuSn") String sku, @Param("isPrimary") boolean isPrimary);

	int updateOccupyStock(@Param("storeCode") String storeCode, @Param("barCode") String barCode,
			@Param("stock") int stock);

	int doCleanOccupy();

	List<ProductSkuStock> findStoreBySku(@Param("productSkuSn") String sku);

}

package com.d2c.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.product.model.ProductSkuOption;

public interface ProductSkuOptionMapper extends SuperMapper<ProductSkuOption> {
	List<ProductSkuOption> findByProductId(Long productId);

	int updateMarkByProductId(@Param("productId") Long productId, @Param("mark") Integer mark);
}

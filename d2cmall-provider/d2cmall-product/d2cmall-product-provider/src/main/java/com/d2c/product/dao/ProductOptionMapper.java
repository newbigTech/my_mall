package com.d2c.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.product.model.ProductOption;
import com.d2c.product.query.ProductOptionSearcher;

public interface ProductOptionMapper extends SuperMapper<ProductOption> {

	List<ProductOption> findBySearcher(@Param("searcher") ProductOptionSearcher searcher,
			@Param("pager") PageModel page);

	Integer countBySearcher(@Param("searcher") ProductOptionSearcher searcher);

	int doRefuse(@Param("id") Long id, @Param("refuseReason") String refuseReason);

	int updateSalesPropertyById(@Param("productId") Long id, @Param("salesProperty") String salesProperty);
}

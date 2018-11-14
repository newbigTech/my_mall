package com.d2c.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.product.model.BrandDetail;
import com.d2c.product.query.BrandDetailSearcher;

public interface BrandDetailMapper extends SuperMapper<BrandDetail> {

	BrandDetail findByBrandId(Long brandId);

	List<BrandDetail> findBySearcher(@Param("searcher") BrandDetailSearcher searcher, @Param("pager") PageModel page);

	Integer countBySearcher(@Param("searcher") BrandDetailSearcher searcher);

}

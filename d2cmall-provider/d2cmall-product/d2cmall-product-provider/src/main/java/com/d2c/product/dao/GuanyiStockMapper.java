package com.d2c.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.product.model.GuanyiStock;

public interface GuanyiStockMapper extends SuperMapper<GuanyiStock> {
	GuanyiStock findLast();

	List<GuanyiStock> findBySearcher(@Param("pager") PageModel page);

	Integer countBySearcher();

	int deleteIgnoreId(Long id);

	GuanyiStock findFirst();
}

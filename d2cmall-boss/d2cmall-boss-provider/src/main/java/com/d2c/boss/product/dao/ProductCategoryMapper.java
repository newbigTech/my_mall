package com.d2c.boss.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.product.model.ProductCategory;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ProductCategoryMapper extends SuperMapper<ProductCategory> {

	int countProductCategoryByQuery(@Param("query") ProQuery query);

	List<ProductCategory> findProductCategoryByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	void delAll();

	void insertToCrm();

	void updateToCrm();

	void upDateStatus();
}
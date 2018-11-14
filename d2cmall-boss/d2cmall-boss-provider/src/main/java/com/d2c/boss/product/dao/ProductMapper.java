package com.d2c.boss.product.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.product.model.Product;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ProductMapper extends SuperMapper<Product> {

	Product findProductBySn(@Param("ProductSn") String ProductSn);

	int countProductsByQuery(@Param("query") ProQuery query);

	List<Product> findProductsByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	Date getLastSysDate();

	void updateOnlineProduct();

	void insertOnlineProduct();

	void updateOfflineProduct();

	void insertOfflineProduct();

	void updateOnlineStatus();

	void updateOfflineStatus();
}
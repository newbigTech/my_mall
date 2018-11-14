package com.d2c.boss.product.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.product.model.ProductSku;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ProductSkuMapper extends SuperMapper<ProductSku> {

	int countProductSkusByQuery(@Param("query") ProQuery query);

	List<ProductSku> findProductSkusByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	List<ProductSku> findProductSkusByProductSn(@Param("productSn") String productSn);

	Date getLastSysDate();

	void updateOnlineProductSku();

	void insertOnlineProductSku();

	void updateOfflineProductSku();

	void insertOfflineProductSku();

	void updateOnlineStatus();

	void updateOfflineStatus();
}

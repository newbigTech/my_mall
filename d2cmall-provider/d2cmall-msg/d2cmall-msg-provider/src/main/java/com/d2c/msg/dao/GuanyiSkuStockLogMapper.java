package com.d2c.msg.dao;

import org.apache.ibatis.annotations.Param;

import com.d2c.msg.model.GuanyiSkuStockLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface GuanyiSkuStockLogMapper extends SuperMapper<GuanyiSkuStockLog> {

	int updateBySku(@Param("sku") String sku, @Param("stock") Integer stock);

}

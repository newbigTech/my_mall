package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.ProductLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ProductLogMapper extends SuperMapper<ProductLog> {

	List<ProductLog> findByProductId(@Param("productId") Long productId, @Param("page") PageModel page);

	int countByProductId(Long productId);

}

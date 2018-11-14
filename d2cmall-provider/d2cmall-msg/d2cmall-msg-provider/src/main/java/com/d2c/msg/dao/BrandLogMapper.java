package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.BrandLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface BrandLogMapper extends SuperMapper<BrandLog> {

	List<BrandLog> findByDesignerId(@Param("designerId") Long designerId, @Param("page") PageModel page);

	int countByDesignerId(Long designerId);
}

package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.DesignersLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface DesignersLogMapper extends SuperMapper<DesignersLog> {

	List<DesignersLog> findByDesignersId(@Param("designersId") Long designersId, @Param("page") PageModel page);

	int countByDesignersId(@Param("designersId") Long designersId);

}

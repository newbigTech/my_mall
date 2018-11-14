package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.FieldDefine;
import com.d2c.mybatis.mapper.SuperMapper;

public interface FieldDefineMapper extends SuperMapper<FieldDefine> {

	List<FieldDefine> findByPageDefId(@Param("pageDefId") Long pageDefId);

	List<FieldDefine> findByPageDefIdWithStatus(@Param("pageDefId") Long pageDefId, @Param("status") int status);

	FieldDefine findOne(@Param("pageDefId") Long pageDefId, @Param("code") String code);

}

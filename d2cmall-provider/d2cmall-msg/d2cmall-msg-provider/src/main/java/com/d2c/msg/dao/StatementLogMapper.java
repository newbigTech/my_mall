package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.msg.model.StatementLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface StatementLogMapper extends SuperMapper<StatementLog> {

	List<StatementLog> findByStatementId(@Param("id") Long id);

}

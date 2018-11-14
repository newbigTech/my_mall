package com.d2c.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.LogisticsPostage;

public interface LogisticsPostageMapper extends SuperMapper<LogisticsPostage> {

	int insertPostage(@Param("postageSettings") List<LogisticsPostage> postageSettings, Long templateId);

	List<LogisticsPostage> findByTemplateId(@Param("id") Long id);

	int insertBatch(@Param("postageSettings") List<LogisticsPostage> postageSettings,
			@Param("templateId") Long templateId);

	int deleteById(@Param("id") Long id);
}

package com.d2c.order.service;

import java.util.List;

import com.d2c.order.model.LogisticsPostage;

public interface LogisticsPostageService {

	int insertBatch(List<LogisticsPostage> logisticsPostage, Long id);

	List<LogisticsPostage> findByTemplateId(Long id);

	int update(LogisticsPostage logisticsPostage);

	int delete(Long id);

	LogisticsPostage insert(LogisticsPostage postage, Long templateId);

}

package com.d2c.msg.service;

import java.util.List;

import com.d2c.msg.model.StatementLog;

public interface StatementLogService {

	StatementLog insert(StatementLog statementLog);

	List<StatementLog> findByStatementId(Long id);

}

package com.d2c.msg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.msg.dao.StatementLogMapper;
import com.d2c.msg.model.StatementLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("statementLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class StatementLogServiceImpl extends ListServiceImpl<StatementLog> implements StatementLogService {

	@Autowired
	private StatementLogMapper statementLogMapper;

	@Override
	public StatementLog insert(StatementLog statementLog) {
		return this.save(statementLog);
	}

	@Override
	public List<StatementLog> findByStatementId(Long id) {
		return statementLogMapper.findByStatementId(id);
	}

}

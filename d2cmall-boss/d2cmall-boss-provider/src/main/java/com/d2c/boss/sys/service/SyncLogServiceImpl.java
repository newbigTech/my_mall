package com.d2c.boss.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.sys.dao.SyncLogMapper;
import com.d2c.boss.sys.model.SyncLog;
import com.d2c.boss.sys.support.SyncLogQuery;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("syncLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SyncLogServiceImpl extends ListServiceImpl<SyncLog> implements SyncLogService {

	@Autowired
	private SyncLogMapper syncLogMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int refreshLog(SyncLog log) {
		return syncLogMapper.refreshLog(log);
	}

	@Override
	public List<SyncLog> findAll(SyncLogQuery queryModel) {
		return syncLogMapper.findAll(queryModel);
	}

	@Override
	public int findStatusBySyncType(String syncType) {
		return syncLogMapper.findStatusBySyncType(syncType);
	}

}

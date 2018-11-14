package com.d2c.boss.sys.service;

import java.util.List;

import com.d2c.boss.sys.model.SyncLog;
import com.d2c.boss.sys.support.SyncLogQuery;

public interface SyncLogService {

	int refreshLog(SyncLog log);

	List<SyncLog> findAll(SyncLogQuery queryModel);

	int findStatusBySyncType(String syncType);
}

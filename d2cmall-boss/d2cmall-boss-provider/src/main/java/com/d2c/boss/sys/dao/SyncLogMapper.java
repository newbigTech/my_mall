package com.d2c.boss.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.sys.model.SyncLog;
import com.d2c.boss.sys.support.SyncLogQuery;
import com.d2c.mybatis.mapper.SuperMapper;

public interface SyncLogMapper extends SuperMapper<SyncLog> {
	int refreshLog(@Param("log") SyncLog log);

	List<SyncLog> findAll(@Param("queryModel") SyncLogQuery queryModel);

	int findStatusBySyncType(@Param("syncType") String syncType);
}

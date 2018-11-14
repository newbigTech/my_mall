package com.d2c.boss.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.sys.dao.ApiLogMapper;
import com.d2c.boss.sys.model.ApiLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("apiLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ApiLogServiceImpl extends ListServiceImpl<ApiLog> implements ApiLogService {

	@Autowired
	private ApiLogMapper apiLogMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ApiLog insert(ApiLog apiLog) {
		ApiLog result = this.save(apiLog);
		return result;
	}

}

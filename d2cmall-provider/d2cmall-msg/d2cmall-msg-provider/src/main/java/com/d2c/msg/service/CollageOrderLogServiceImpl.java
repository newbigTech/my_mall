package com.d2c.msg.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.msg.model.CollageOrderLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service(value = "collageOrderLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CollageOrderLogServiceImpl extends ListServiceImpl<CollageOrderLog> implements CollageOrderLogService {

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public CollageOrderLog insert(CollageOrderLog collageOrderLog) {
		return this.save(collageOrderLog);
	}

}

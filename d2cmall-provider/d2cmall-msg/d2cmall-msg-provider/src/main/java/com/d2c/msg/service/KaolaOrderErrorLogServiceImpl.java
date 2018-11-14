package com.d2c.msg.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.msg.dao.KaolaOrderErrorLogMapper;
import com.d2c.msg.model.KaolaOrderErrorLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("kaolaOrderErrorLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class KaolaOrderErrorLogServiceImpl extends ListServiceImpl<KaolaOrderErrorLog>
		implements KaolaOrderErrorLogService {
	@Resource
	private KaolaOrderErrorLogMapper kaolaOrderErrorLogMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public KaolaOrderErrorLog insert(KaolaOrderErrorLog kaolaOrderErrorLog) {
		return this.save(kaolaOrderErrorLog);
	}

}

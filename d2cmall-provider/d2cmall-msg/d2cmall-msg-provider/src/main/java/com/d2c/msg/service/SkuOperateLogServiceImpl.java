package com.d2c.msg.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.msg.model.SkuOperateLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("skuOperateLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class SkuOperateLogServiceImpl extends ListServiceImpl<SkuOperateLog> implements SkuOperateLogService {

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public SkuOperateLog insert(SkuOperateLog skuOperateLog) {
		return this.save(skuOperateLog);
	}

}

package com.d2c.msg.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.msg.model.DiscountSettingLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("discountSettingLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class DiscountSettingLogServiceImpl extends ListServiceImpl<DiscountSettingLog>
		implements DiscountSettingLogService {

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public DiscountSettingLog insert(DiscountSettingLog log) {
		return this.save(log);
	}

}

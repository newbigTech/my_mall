package com.d2c.msg.service;

import com.d2c.msg.model.SkuOperateLog;

public interface SkuOperateLogService {

	/**
	 * 添加一天账户日志记录
	 * 
	 * @param accountLog
	 * @return
	 */
	SkuOperateLog insert(SkuOperateLog skuOperateLog);

}

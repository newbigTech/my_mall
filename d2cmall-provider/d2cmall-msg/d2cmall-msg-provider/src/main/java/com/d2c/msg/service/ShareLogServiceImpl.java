package com.d2c.msg.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.msg.model.ShareLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("shareLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class ShareLogServiceImpl extends ListServiceImpl<ShareLog> implements ShareLogService {

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ShareLog insert(ShareLog shareLog) {
		return save(shareLog);
	}

}

package com.d2c.boss.offline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.offline.dao.OfflineOrderItemMapper;
import com.d2c.boss.offline.model.OfflineOrderItem;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("offlineOrderItemService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OfflineOrderItemServiceImpl extends ListServiceImpl<OfflineOrderItem> implements OfflineOrderItemService {

	@Autowired
	private OfflineOrderItemMapper offlineOrderItemMapper;

	@Override
	public int updateStatus() {
		return offlineOrderItemMapper.updateStatus();
	}

	@Override
	public void deleteSfOrderItem() {
		offlineOrderItemMapper.deleteSfOrderItem();
	}

	@Override
	public void updateSuccessStatus() {
		offlineOrderItemMapper.updateSuccessStatus();
	}

	@Override
	public void updateReturnStatus() {
		offlineOrderItemMapper.updateReturnStatus();
	}

	@Override
	public void updateRecopyStatus() {
		offlineOrderItemMapper.updateRecopyStatus();
	}

}

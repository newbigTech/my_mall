package com.d2c.boss.offline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.offline.dao.OfflineOrderMapper;
import com.d2c.boss.offline.model.OfflineOrder;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("offlineOrderService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OfflineOrderServiceImpl extends ListServiceImpl<OfflineOrder> implements OfflineOrderService {

	@Autowired
	private OfflineOrderMapper offlineOrderMapper;

	@Override
	public int updateStatus() {
		return offlineOrderMapper.updateStatus();
	}

	@Override
	public void updateSuccessStatus() {
		offlineOrderMapper.updateSuccessStatus();
	}

	@Override
	public void updateReturnStatus() {
		offlineOrderMapper.updateReturnStatus();
	}

	@Override
	public void updateRecopyStatus() {
		offlineOrderMapper.updateRecopyStatus();
	}

	@Override
	public void deleteNotActive() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSfOrder() {
		offlineOrderMapper.deleteSfOrder();

	}

}

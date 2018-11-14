package com.d2c.msg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.msg.dao.BossDeviceMapper;
import com.d2c.msg.model.BossDevice;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("bossDeviceService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BossDeviceServiceImpl extends ListServiceImpl<BossDevice> implements BossDeviceService {

	@Autowired
	private BossDeviceMapper bossDeviceMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int doInsert(BossDevice bossDevice) {
		return bossDeviceMapper.doInsert(bossDevice);
	}

	public BossDevice findByMemberId(Long memberId) {
		return bossDeviceMapper.findByMemberId(memberId);
	}

}

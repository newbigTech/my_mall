package com.d2c.msg.service;

import com.d2c.msg.model.BossDevice;

public interface BossDeviceService {

	int doInsert(BossDevice bossDevice);

	BossDevice findByMemberId(Long memberId);

}

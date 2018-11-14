package com.d2c.msg.dao;

import com.d2c.msg.model.BossDevice;
import com.d2c.mybatis.mapper.SuperMapper;

public interface BossDeviceMapper extends SuperMapper<BossDevice> {

	int doInsert(BossDevice bossDevice);

	BossDevice findByMemberId(Long memberId);

}
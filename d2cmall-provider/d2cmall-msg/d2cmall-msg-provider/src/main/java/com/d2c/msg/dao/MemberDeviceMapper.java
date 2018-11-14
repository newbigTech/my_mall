package com.d2c.msg.dao;

import com.d2c.msg.model.MemberDevice;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MemberDeviceMapper extends SuperMapper<MemberDevice> {

	int doInsert(MemberDevice memberDevice);

	MemberDevice findByMemberId(Long memberId);

}
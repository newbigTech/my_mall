package com.d2c.msg.service;

import com.d2c.msg.model.MemberDevice;

public interface MemberDeviceService {

	int doInsert(MemberDevice memberDevice);

	MemberDevice findByMemberId(Long memberId);

}

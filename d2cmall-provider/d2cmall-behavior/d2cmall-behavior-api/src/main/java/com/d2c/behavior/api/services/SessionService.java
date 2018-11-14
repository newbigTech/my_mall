package com.d2c.behavior.api.services;

import com.d2c.behavior.api.mongo.model.PersonDeviceDO;
import com.d2c.behavior.api.mongo.model.PersonSessionDO;
import com.d2c.member.model.Member;
import com.d2c.member.model.MemberInfo;

public interface SessionService {
	
	public PersonDeviceDO findDeviceById(String deviceId);

	public PersonSessionDO nextSession(PersonDeviceDO device, MemberInfo memberInfo, String token) ;
	
	public PersonSessionDO onThirdLogin(PersonSessionDO session, Member member);
	
	public PersonSessionDO onLogin(PersonSessionDO session, MemberInfo memberInfo);
	
	public void updateSessionClose(String sessionId);
	
	public PersonSessionDO findLastSessionByMemberId(Long memberInfoId);
	
}

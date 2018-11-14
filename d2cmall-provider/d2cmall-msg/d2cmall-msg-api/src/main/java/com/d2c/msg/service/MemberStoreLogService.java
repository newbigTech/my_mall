package com.d2c.msg.service;

import com.d2c.msg.model.MemberStoreLog;

public interface MemberStoreLogService {

	MemberStoreLog findByLoginCode(String loginCode);

	MemberStoreLog insert(MemberStoreLog memberStoreLog);
}

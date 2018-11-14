package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.online.model.OnlineMemberInfo;

public interface OnlineMemberInfoService {

	void save(List<OnlineMemberInfo> onlineMemberInfos);

	Date getLastSysDate();

	int updateStatus();

	OnlineMemberInfo findById(Long id);
}

package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.online.model.OnlineMember;

public interface OnlineMemberService {

	void save(List<OnlineMember> onlineMembers);

	Date getLastSysDate();

	int updateStatus();

	OnlineMember findById(Long id);
}

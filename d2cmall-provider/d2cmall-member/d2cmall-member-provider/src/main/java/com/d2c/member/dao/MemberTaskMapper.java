package com.d2c.member.dao;

import com.d2c.member.model.MemberTask;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MemberTaskMapper extends SuperMapper<MemberTask> {

	public void updateExecStart();
	
	public void updateExecEnd();

}
package com.d2c.member.mongo.services;

import java.util.List;

import com.d2c.member.model.MemberInfo;
import com.d2c.member.mongo.model.MemberTaskExecDO;
import com.d2c.member.view.MemberTaskVO;

public interface MemberTaskExecService {
	
	MemberTaskExecDO taskDone(MemberInfo member, String codeType, String subCode);
	
	long refreshOnDayTask();

	List<MemberTaskVO> findTaskList(Long memberId);
	
}

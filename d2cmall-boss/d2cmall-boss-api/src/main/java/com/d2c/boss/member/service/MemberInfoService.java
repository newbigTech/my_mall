package com.d2c.boss.member.service;

import com.d2c.boss.member.dto.MemberInfoDto;
import com.d2c.boss.member.model.MemberInfo;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface MemberInfoService {

	PageResult<MemberInfo> findMemberInfosByQuery(PageModel page, ProQuery query);

	MemberInfo insert(MemberInfo memberInfo);

	PageResult<MemberInfoDto> findMemberDtosByQuery(PageModel page, ProQuery query);

	void updateDevices();

	void insertOnlineMemberInfo();

	void updateOnlineStatus();

	void insertOfflineMemberInfo();

	void updateOfflineStatus();
}

package com.d2c.boss.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.member.model.MemberInfo;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MemberInfoMapper extends SuperMapper<MemberInfo> {

	int countMemberInfosByQuery(@Param("query") ProQuery query);

	List<MemberInfo> findMemberInfosByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	void updateIos();

	void updateAndroid();

	void updatePc();

	void updateOnlineStatus();

	void insertOnlineMemberInfo();

	void updateOfflineStatus();

	void insertOfflineMemberInfo();
}

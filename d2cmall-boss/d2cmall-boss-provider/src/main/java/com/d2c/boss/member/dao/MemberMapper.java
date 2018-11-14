package com.d2c.boss.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.member.model.Member;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MemberMapper extends SuperMapper<Member> {

	int countMembersByQuery(@Param("query") ProQuery query);

	List<Member> findMembersByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	List<Member> findMembersByLoginCode(@Param("loginCode") String loginCode);

	void insertOnlineMember();

	void updateOnlineStatus();
}

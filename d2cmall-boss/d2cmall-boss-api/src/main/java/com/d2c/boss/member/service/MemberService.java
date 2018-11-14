package com.d2c.boss.member.service;

import java.util.List;

import com.d2c.boss.member.model.Member;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface MemberService {

	PageResult<Member> findMembersByQuery(PageModel page, ProQuery query);

	Member insert(Member member);

	List<Member> findMembersByLoginCode(String loginCode);

	void insertOnlineMember();

	void updateOnlineStatus();

}

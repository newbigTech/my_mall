package com.d2c.boss.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.member.dao.MemberMapper;
import com.d2c.boss.member.model.Member;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("memberService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MemberServiceImpl extends ListServiceImpl<Member> implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public PageResult<Member> findMembersByQuery(PageModel page, ProQuery query) {
		PageResult<Member> pager = new PageResult<Member>(page);
		int count = memberMapper.countMembersByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(memberMapper.findMembersByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Member insert(Member member) {
		Member result = this.save(member);
		return result;
	}

	@Override
	public List<Member> findMembersByLoginCode(String loginCode) {
		return memberMapper.findMembersByLoginCode(loginCode);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void insertOnlineMember() {
		memberMapper.insertOnlineMember();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateOnlineStatus() {
		memberMapper.updateOnlineStatus();
	}

}

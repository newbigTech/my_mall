package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.online.dao.OnlineMemberMapper;
import com.d2c.boss.online.model.OnlineMember;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("onlineMemberService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OnlineMemberServiceImpl extends ListServiceImpl<OnlineMember> implements OnlineMemberService {

	@Autowired
	private OnlineMemberMapper onlineMemberMapper;

	@Override
	public void save(List<OnlineMember> onlineMembers) {
	}

	@Override
	public Date getLastSysDate() {
		return onlineMemberMapper.getLastSysDate();
	}

	@Override
	public int updateStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OnlineMember findById(Long id) {
		return this.findOneById(id);
	}

}

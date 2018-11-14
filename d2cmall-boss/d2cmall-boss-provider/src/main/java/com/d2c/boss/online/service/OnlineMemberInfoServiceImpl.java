package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.online.dao.OnlineMemberInfoMapper;
import com.d2c.boss.online.model.OnlineMemberInfo;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("onlineMemberInfoService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OnlineMemberInfoServiceImpl extends ListServiceImpl<OnlineMemberInfo> implements OnlineMemberInfoService {

	@Autowired
	private OnlineMemberInfoMapper onlineMemberInfoMapper;

	@Override
	public void save(List<OnlineMemberInfo> onlineMemberInfos) {
	}

	@Override
	public Date getLastSysDate() {
		return onlineMemberInfoMapper.getLastSysDate();
	}

	@Override
	public int updateStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OnlineMemberInfo findById(Long id) {
		return this.findOneById(id);
	}

}

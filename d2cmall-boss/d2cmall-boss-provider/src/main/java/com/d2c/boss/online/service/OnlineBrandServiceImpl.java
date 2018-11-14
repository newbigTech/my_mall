package com.d2c.boss.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.online.dao.OnlineBrandMapper;
import com.d2c.boss.online.model.OnlineBrand;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("onlineBrandService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OnlineBrandServiceImpl extends ListServiceImpl<OnlineBrand> implements OnlineBrandService {

	@Autowired
	private OnlineBrandMapper onlineBrandMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateStatus() {
		return onlineBrandMapper.updateStatus();
	}

}

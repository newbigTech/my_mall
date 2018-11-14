package com.d2c.boss.offline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.offline.dao.OfflineBrandMapper;
import com.d2c.boss.offline.model.OfflineBrand;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("offlineBrandService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OfflineBrandServiceImpl extends ListServiceImpl<OfflineBrand> implements OfflineBrandService {
	@Autowired
	private OfflineBrandMapper offlineBrandMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateStatus() {
		return offlineBrandMapper.updateStatus();
	}

}

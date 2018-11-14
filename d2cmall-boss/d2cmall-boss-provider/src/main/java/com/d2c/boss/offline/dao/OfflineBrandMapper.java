package com.d2c.boss.offline.dao;

import com.d2c.boss.offline.model.OfflineBrand;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OfflineBrandMapper extends SuperMapper<OfflineBrand> {
	int updateStatus();
}

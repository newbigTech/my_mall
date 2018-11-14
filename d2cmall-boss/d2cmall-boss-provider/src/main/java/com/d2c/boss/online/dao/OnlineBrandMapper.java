package com.d2c.boss.online.dao;

import com.d2c.boss.online.model.OnlineBrand;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OnlineBrandMapper extends SuperMapper<OnlineBrand> {
	int updateStatus();
}

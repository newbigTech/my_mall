package com.d2c.boss.offline.dao;

import com.d2c.boss.offline.model.OfflineOrder;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OfflineOrderMapper extends SuperMapper<OfflineOrder> {

	int updateSuccessStatus();

	int updateReturnStatus();

	int updateRecopyStatus();

	int deleteSfOrder();

	int updateStatus();

}

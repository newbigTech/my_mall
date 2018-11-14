package com.d2c.boss.offline.dao;

import com.d2c.boss.offline.model.OfflineOrderItem;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OfflineOrderItemMapper extends SuperMapper<OfflineOrderItem> {

	void deleteSfOrderItem();

	int updateStatus();

	void updateSuccessStatus();

	void updateReturnStatus();

	void updateRecopyStatus();
}

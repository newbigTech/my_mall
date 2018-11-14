package com.d2c.boss.offline.service;

public interface OfflineOrderItemService {

	int updateStatus();

	void deleteSfOrderItem();

	void updateSuccessStatus();

	void updateReturnStatus();

	void updateRecopyStatus();
}

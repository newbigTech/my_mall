package com.d2c.boss.offline.service;

public interface OfflineOrderService {

	int updateStatus();

	void updateSuccessStatus();

	void updateReturnStatus();

	void updateRecopyStatus();

	void deleteNotActive();

	void deleteSfOrder();
}

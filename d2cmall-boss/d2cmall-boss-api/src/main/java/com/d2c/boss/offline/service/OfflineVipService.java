package com.d2c.boss.offline.service;

import java.util.List;

import com.d2c.boss.offline.model.OfflineVip;

public interface OfflineVipService {

	void save(List<OfflineVip> list);

	OfflineVip findByCardNo(String cardNo);
}

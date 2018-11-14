package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.online.model.OnlineSku;

public interface OnlineSkuService {
	
	void save(List<OnlineSku> list);

	Date getLastSysDate();

	int updateStatus();

	OnlineSku findByBarCode(String barCode);
}

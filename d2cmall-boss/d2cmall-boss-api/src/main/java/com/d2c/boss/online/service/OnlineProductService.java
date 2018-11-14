package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.online.model.OnlineProduct;

public interface OnlineProductService {

	void save(List<OnlineProduct> products);

	Date getLastSysDate();

	int updateStatus();

	OnlineProduct findByInernalSn(String InernalSn);

}

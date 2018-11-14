package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.online.model.OnlineOrder;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface OnlineOrderService {

	void save(List<OnlineOrder> orders);

	Date getLastSysDate();

	void insertNewToOrders();

	void upDateOldToOrders(OnlineOrder onlineOrder);

	void upStatusByOrderSn(int status, String orderSn);

	OnlineOrder findByOrderSn(String orderSn);

	PageResult<OnlineOrder> findModifyOnlineOrders(PageModel page);

	void updateSource();

	void updateStatus();
}

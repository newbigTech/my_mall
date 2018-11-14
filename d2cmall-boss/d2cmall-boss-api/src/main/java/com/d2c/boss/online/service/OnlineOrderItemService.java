package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.online.model.OnlineOrderItem;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface OnlineOrderItemService {

	void save(List<OnlineOrderItem> orderItems);

	Date getLastSysDate();

	int updateStatus();

	void insertNewToOrderItems();

	OnlineOrderItem findBySourceId(String sourceId);

	OnlineOrderItem findById(Long id);

	PageResult<OnlineOrderItem> findModifyOnlineOrderItems(PageModel page);
}

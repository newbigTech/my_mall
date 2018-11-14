package com.d2c.boss.order.service;

import com.d2c.boss.order.dto.OrderDto;
import com.d2c.boss.order.model.Order;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface OrderService {

	PageResult<Order> findOrdersByQuery(PageModel page, ProQuery query);

	Order insert(Order order);

	PageResult<OrderDto> findOrderDtosByQuery(PageModel page, ProQuery query);

	int upDateOldToOrders(Order order);

	void insertOfflineOrders();

	void updateOfflineOrders();

	void updateOnlineOrders();

	void insertOnlineOrders();
}

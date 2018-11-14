package com.d2c.boss.order.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.order.dto.DaySalesCount;
import com.d2c.boss.order.model.OrderItem;
import com.d2c.boss.order.support.OrderItemQuery;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface OrderItemService {

	PageResult<OrderItem> findOrderItemsByQuery(PageModel page, ProQuery query);

	List<OrderItem> findByOrderSn(String orderSn);

	OrderItem insert(OrderItem orderItem);

	int upDateOldToOrderItems(OrderItem orderItem);

	void insertOfflineOrderItems();

	void updateOfflineOrderItems();

	void insertOnlineOrderItems();

	void updateOnlineOrderItems();

	// 线上查询
	Integer countSalesOrderItem(Date beginDate, Date endDate, String[] brands, OrderItemQuery query);

	Integer countDeliveryOrderItem(Date beginDate, Date endDate, String[] brands, OrderItemQuery query);

	Integer countCloseOrderItem(Date beginDate, Date endDate, String[] brands, OrderItemQuery query);

	PageResult<OrderItem> findSalesOrderItem(Date beginDate, Date endDate, String[] brands, PageModel pager,
			OrderItemQuery query);

	PageResult<OrderItem> findDeliveryOrderItem(Date beginDate, Date endDate, String[] brands, PageModel pager,
			OrderItemQuery query);

	PageResult<OrderItem> findCloseOrderItem(Date beginDate, Date endDate, String[] brands, PageModel pager,
			OrderItemQuery query);

	String findHotProduct(Date beginDate, Date endDate, String[] brands);

	List<DaySalesCount> countByDay(String[] brands);
}

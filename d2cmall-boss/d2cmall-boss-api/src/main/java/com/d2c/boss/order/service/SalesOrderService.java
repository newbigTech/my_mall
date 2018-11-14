package com.d2c.boss.order.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.d2c.boss.order.dto.TodaySummaryDto;
import com.d2c.boss.order.model.SalesOrder;

public interface SalesOrderService {

	SalesOrder findByOrderSn(String orderSn);

	void save(List<SalesOrder> orders);

	List<Map<String, Object>> getCitySales();

	List<String> getPayType();

	List<Integer> getStatus();

	void updateOnline();

	void insertOnline();

	void updateOffline();

	void insertOffline();

	List<Map<String, Object>> getAllMonth();

	List<Map<String, Object>> getStoreMonthly(Integer year, Integer month);

	TodaySummaryDto todayOrder(Date now);
}

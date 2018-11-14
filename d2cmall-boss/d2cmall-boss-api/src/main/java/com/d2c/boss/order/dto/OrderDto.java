package com.d2c.boss.order.dto;

import java.util.List;

import com.d2c.boss.order.model.Order;
import com.d2c.boss.order.model.OrderItem;

public class OrderDto extends Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单明细
	 */
	private List<OrderItem> orderItems;

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}

package com.d2c.boss.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.order.model.Order;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OrderMapper extends SuperMapper<Order> {

	int countOrdersByQuery(@Param("query") ProQuery query);

	List<Order> findOrdersByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	void insertOfflineOrders();

	void updateOfflineOrders();

	void insertOnlineOrders();

	void updateOnlineOrders();
}

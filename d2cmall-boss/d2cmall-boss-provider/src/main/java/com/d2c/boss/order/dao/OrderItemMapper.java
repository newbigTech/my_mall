package com.d2c.boss.order.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.order.dto.DaySalesCount;
import com.d2c.boss.order.model.OrderItem;
import com.d2c.boss.order.support.OrderItemQuery;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OrderItemMapper extends SuperMapper<OrderItem> {

	int countOrderItemsByQuery(@Param("query") ProQuery query);

	List<OrderItem> findOrderItemsByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	List<OrderItem> findByOrderSn(@Param("orderSn") String orderSn);

	void insertOfflineOrderItems();

	void updateOfflineOrderItems();

	void insertOnlineOrderItems();

	void updateOnlineOrderItems();

	Integer countSalesOrderItem(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,
			@Param("brands") String[] brands, @Param("query") OrderItemQuery query);

	Integer countDeliveryOrderItem(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,
			@Param("brands") String[] brands, @Param("query") OrderItemQuery query);

	Integer countCloseOrderItem(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,
			@Param("brands") String[] brands, @Param("query") OrderItemQuery query);

	List<OrderItem> findSalesOrderItem(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,
			@Param("brands") String[] brands, @Param("page") PageModel page, @Param("query") OrderItemQuery query);

	List<OrderItem> findDeliveryOrderItem(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,
			@Param("brands") String[] brands, @Param("page") PageModel page, @Param("query") OrderItemQuery query);

	List<OrderItem> findCloseOrderItem(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,
			@Param("brands") String[] brands, @Param("page") PageModel page, @Param("query") OrderItemQuery query);

	String findHotProduct(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate,
			@Param("brands") String[] brands);

	List<DaySalesCount> countByDay(@Param("brands") String[] brands);
}

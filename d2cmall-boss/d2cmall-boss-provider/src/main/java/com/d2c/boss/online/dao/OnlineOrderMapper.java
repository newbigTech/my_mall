package com.d2c.boss.online.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.online.model.OnlineOrder;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OnlineOrderMapper extends SuperMapper<OnlineOrder> {

	Date getLastSysDate();

	OnlineOrder findByOrderSn(@Param("orderSn") String orderSn);

	void insertToOrders();

	int updateStatus(@Param("oldStatus") int oldStatus, @Param("newStatus") int newStatus);

	int upStatusByOrderSn(@Param("status") int status, @Param("orderSn") String orderSn);

	int countModifyOnlineOrders();

	List<OnlineOrder> findModifyOnlineOrders(@Param("pager") PageModel page);

	int updateToOrdersByOrderSn(@Param("onlineOrder") OnlineOrder onlineOrder);

	void updateSourceNull();

	void updateSourceIos();

	void updateSourceAndroid();
}
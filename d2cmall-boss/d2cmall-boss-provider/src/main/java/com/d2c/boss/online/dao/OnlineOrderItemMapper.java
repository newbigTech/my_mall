package com.d2c.boss.online.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.online.model.OnlineOrderItem;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OnlineOrderItemMapper extends SuperMapper<OnlineOrderItem> {

	Date getLastSysDate();

	OnlineOrderItem findByOrderSn(@Param("orderSn") String orderSn);

	int updateStatus();

	void insertNewToOrderItems();

	int updateStatus(@Param("oldStatus") int oldStatus, @Param("newStatus") int newStatus);

	int countModifyOnlineOrderItems();

	List<OnlineOrderItem> findModifyOnlineOrderItems(@Param("pager") PageModel page);
}
package com.d2c.order.service;

import java.util.List;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.order.model.GuanyiOrderItem;
import com.d2c.order.query.GuanyiOrderItemSearcher;

public interface GuanyiOrderItemService {

	GuanyiOrderItem findById(Long id);

	GuanyiOrderItem insert(GuanyiOrderItem guanyiOrderItem);

	List<GuanyiOrderItem> findByOrderId(Long orderId);

	void processExpress(final GuanyiOrderItem goi, boolean reProcess, String operator) throws Exception;

	PageResult<GuanyiOrderItem> findBySearcher(GuanyiOrderItemSearcher searcher, PageModel page);

	Integer countBySearcher(GuanyiOrderItemSearcher searcher);

	int update(GuanyiOrderItem guanyiOrderItem);

	int doHandle(Long id, String handleMan, String handleContent);
}

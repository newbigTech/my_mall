package com.d2c.boss.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.order.dao.OrderItemMapper;
import com.d2c.boss.order.dto.DaySalesCount;
import com.d2c.boss.order.model.OrderItem;
import com.d2c.boss.order.model.OrderItemExample;
import com.d2c.boss.order.model.OrderItemExample.Criteria;
import com.d2c.boss.order.support.OrderItemQuery;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("orderItemService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OrderItemServiceImpl extends ListServiceImpl<OrderItem> implements OrderItemService {

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Override
	public PageResult<OrderItem> findOrderItemsByQuery(PageModel page, ProQuery query) {

		PageResult<OrderItem> pager = new PageResult<>(page);
		int count = orderItemMapper.countOrderItemsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(orderItemMapper.findOrderItemsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public OrderItem insert(OrderItem orderItem) {
		OrderItem result = this.save(orderItem);
		return result;
	}

	@Override
	public List<OrderItem> findByOrderSn(String orderSn) {
		return this.orderItemMapper.findByOrderSn(orderSn);
	}

	@Override
	public int upDateOldToOrderItems(OrderItem orderItem) {
		OrderItemExample example = new OrderItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andSourceIdEqualTo(orderItem.getSourceId());
		return orderItemMapper.updateByConditionSelective(orderItem, example);
	}

	@Override
	public void insertOfflineOrderItems() {
		orderItemMapper.insertOfflineOrderItems();
	}

	@Override
	public void updateOfflineOrderItems() {
		orderItemMapper.updateOfflineOrderItems();
	}

	@Override
	public void insertOnlineOrderItems() {
		orderItemMapper.insertOnlineOrderItems();
	}

	@Override
	public void updateOnlineOrderItems() {
		orderItemMapper.updateOnlineOrderItems();
	}

	@Override
	public Integer countSalesOrderItem(Date beginDate, Date endDate, String[] brands, OrderItemQuery query) {
		return orderItemMapper.countSalesOrderItem(beginDate, endDate, brands, query);
	}

	@Override
	public Integer countDeliveryOrderItem(Date beginDate, Date endDate, String[] brands, OrderItemQuery query) {
		return orderItemMapper.countDeliveryOrderItem(beginDate, endDate, brands, query);
	}

	@Override
	public Integer countCloseOrderItem(Date beginDate, Date endDate, String[] brands, OrderItemQuery query) {
		return orderItemMapper.countCloseOrderItem(beginDate, endDate, brands, query);
	}

	@Override
	public PageResult<OrderItem> findSalesOrderItem(Date beginDate, Date endDate, String[] brands, PageModel page,
			OrderItemQuery query) {
		PageResult<OrderItem> pager = new PageResult<>(page);
		Integer totalCount = orderItemMapper.countSalesOrderItem(beginDate, endDate, brands, query);
		List<OrderItem> list = new ArrayList<>();
		if (totalCount > 0) {
			list = orderItemMapper.findSalesOrderItem(beginDate, endDate, brands, page, query);
		}
		pager.setList(list);
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public PageResult<OrderItem> findDeliveryOrderItem(Date beginDate, Date endDate, String[] brands, PageModel page,
			OrderItemQuery query) {
		PageResult<OrderItem> pager = new PageResult<>(page);
		Integer totalCount = orderItemMapper.countDeliveryOrderItem(beginDate, endDate, brands, query);
		List<OrderItem> list = new ArrayList<>();
		if (totalCount > 0) {
			list = orderItemMapper.findDeliveryOrderItem(beginDate, endDate, brands, page, query);
		}
		pager.setList(list);
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public PageResult<OrderItem> findCloseOrderItem(Date beginDate, Date endDate, String[] brands, PageModel page,
			OrderItemQuery query) {
		PageResult<OrderItem> pager = new PageResult<>(page);
		Integer totalCount = orderItemMapper.countCloseOrderItem(beginDate, endDate, brands, query);
		List<OrderItem> list = new ArrayList<>();
		if (totalCount > 0) {
			list = orderItemMapper.findCloseOrderItem(beginDate, endDate, brands, page, query);
		}
		pager.setList(list);
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public String findHotProduct(Date beginDate, Date endDate, String[] brands) {
		return orderItemMapper.findHotProduct(beginDate, endDate, brands);
	}

	@Override
	public List<DaySalesCount> countByDay(String[] brands) {
		return orderItemMapper.countByDay(brands);
	}

}

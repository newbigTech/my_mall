package com.d2c.boss.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.order.dao.OrderMapper;
import com.d2c.boss.order.dto.OrderDto;
import com.d2c.boss.order.model.Order;
import com.d2c.boss.order.model.OrderExample;
import com.d2c.boss.order.model.OrderExample.Criteria;
import com.d2c.boss.order.model.OrderItem;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.boss.sys.util.BeanUtils;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("orderService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OrderServiceImpl extends ListServiceImpl<Order> implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemService orderItemService;

	@Override
	public PageResult<Order> findOrdersByQuery(PageModel page, ProQuery query) {

		PageResult<Order> pager = new PageResult<Order>(page);
		int count = orderMapper.countOrdersByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(orderMapper.findOrdersByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Order insert(Order order) {
		Order result = this.save(order);
		return result;
	}

	@Override
	public PageResult<OrderDto> findOrderDtosByQuery(PageModel page, ProQuery query) {
		PageResult<OrderDto> pager = new PageResult<OrderDto>(page);
		int count = orderMapper.countOrdersByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		} else {
			List<OrderDto> orderDtos = new ArrayList<OrderDto>();
			List<Order> orders = orderMapper.findOrdersByQuery(page, query);
			for (Order order : orders) {
				OrderDto dto = new OrderDto();
				List<OrderItem> orderItems = orderItemService.findByOrderSn(order.getSn());
				BeanUtils.copyProperties(order, dto);
				dto.setOrderItems(orderItems);
				orderDtos.add(dto);
			}
			pager.setList(orderDtos);
		}
		return pager;
	}

	@Override
	public int upDateOldToOrders(Order order) {
		OrderExample example = new OrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andSnEqualTo(order.getSn());
		return orderMapper.updateByConditionSelective(order, example);
	}

	@Override
	public void insertOfflineOrders() {
		orderMapper.insertOfflineOrders();

	}

	@Override
	public void updateOfflineOrders() {
		orderMapper.updateOfflineOrders();
	}

	@Override
	public void updateOnlineOrders() {
		orderMapper.updateOnlineOrders();
	}

	@Override
	public void insertOnlineOrders() {
		orderMapper.insertOnlineOrders();
	}

}

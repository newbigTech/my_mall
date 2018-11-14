package com.d2c.boss.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.d2c.boss.order.dto.DaySalesCount;
import com.d2c.boss.order.model.OrderItem;
import com.d2c.boss.order.service.OrderItemService;
import com.d2c.boss.order.support.OrderItemQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.api.response.Response;
import com.d2c.common.api.response.SuccessResponse;
import com.d2c.util.date.DateUtil;

@Controller
@RequestMapping("/api/crm/orderitem")
public class OrderItemController extends BaseController {
	@Resource
	private OrderItemService orderItemService;

	@ResponseBody
	@RequestMapping(value = "/sales/count", method = RequestMethod.POST)
	public Response salescount(Long beginDate, Long endDate, String[] brands) {
		SuccessResponse result = new SuccessResponse();
		int salesCount = orderItemService.countSalesOrderItem(beginDate == null ? null : new Date(beginDate),
				endDate == null ? null : new Date(endDate), brands, null);
		result.put("salesCount", salesCount);
		String hotSalesProduct = orderItemService.findHotProduct(beginDate == null ? null : new Date(beginDate),
				endDate == null ? null : new Date(endDate), brands);
		result.put("hotSalesProduct", hotSalesProduct == null ? "" : hotSalesProduct);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/delivery/count", method = RequestMethod.POST)
	public Response deliberycount(Long beginDate, Long endDate, String[] brands) {
		SuccessResponse result = new SuccessResponse();
		int deliveryCount = orderItemService.countDeliveryOrderItem(beginDate == null ? null : new Date(beginDate),
				endDate == null ? null : new Date(endDate), brands, null);
		result.put("deliveryCount", deliveryCount);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/close/count", method = RequestMethod.POST)
	public Response closecount(Long beginDate, Long endDate, String[] brands) {
		SuccessResponse result = new SuccessResponse();
		int closeCount = orderItemService.countCloseOrderItem(beginDate == null ? null : new Date(beginDate),
				endDate == null ? null : new Date(endDate), brands, null);
		result.put("closeCount", closeCount);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/sales/list", method = RequestMethod.POST)
	public Response sales(Long beginDate, Long endDate, String[] brands, PageModel page, String params) {
		SuccessResponse result = new SuccessResponse();
		OrderItemQuery query = null;
		try {
			query = JSONObject.toJavaObject(JSONObject.parseObject(params), OrderItemQuery.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageResult<OrderItem> pager = orderItemService.findSalesOrderItem(
				beginDate == null ? null : new Date(beginDate), endDate == null ? null : new Date(endDate), brands,
				page, query);
		result.put("pager", pager);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/delivery/list", method = RequestMethod.POST)
	public Response delivery(Long beginDate, Long endDate, String[] brands, PageModel page, String params) {
		SuccessResponse result = new SuccessResponse();
		OrderItemQuery query = null;
		try {
			query = JSONObject.toJavaObject(JSONObject.parseObject(params), OrderItemQuery.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageResult<OrderItem> pager = orderItemService.findDeliveryOrderItem(
				beginDate == null ? null : new Date(beginDate), endDate == null ? null : new Date(endDate), brands,
				page, query);
		result.put("pager", pager);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/close/list", method = RequestMethod.POST)
	public Response close(Long beginDate, Long endDate, String[] brands, PageModel page, String params) {
		SuccessResponse result = new SuccessResponse();
		OrderItemQuery query = null;
		try {
			query = JSONObject.toJavaObject(JSONObject.parseObject(params), OrderItemQuery.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageResult<OrderItem> pager = orderItemService.findCloseOrderItem(
				beginDate == null ? null : new Date(beginDate), endDate == null ? null : new Date(endDate), brands,
				page, query);
		result.put("pager", pager);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/countbyday", method = RequestMethod.POST)
	public Response countByDay(String[] brands) {
		SuccessResponse result = new SuccessResponse();
		List<DaySalesCount> list = orderItemService.countByDay(brands);
		Map<String, Integer> map = new HashMap<>();
		list.forEach(s -> map.put(s.getTime(), s.getCount()));
		List<DaySalesCount> recentlySales = new ArrayList<>();
		long timestamp = 24 * 3600 * 1000L;
		long now = System.currentTimeMillis();
		for (int i = 6; i >= 0; i--) {
			Date date = new Date(now - i * timestamp);
			String time = DateUtil.day2str(date);
			if (map.containsKey(time)) {
				recentlySales.add(new DaySalesCount(time, map.get(time)));
			} else {
				recentlySales.add(new DaySalesCount(time, 0));
			}
		}
		result.put("list", recentlySales);
		return result;
	}
}

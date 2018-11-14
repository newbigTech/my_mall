package com.d2c.flame.controller.behavior;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.d2c.behavior.api.services.EventQueryService;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.api.response.ResponseResult;
import com.d2c.common.api.response.ResultHandler;
import com.d2c.flame.controller.base.BaseSessionController;
import com.d2c.member.model.MemberInfo;

/**
 * 用户行为
 * 
 * @author wull
 */
@RestController
@RequestMapping(value = "/v3/api/behavior/event/find")
public class EventQueryController extends BaseSessionController {

	@Reference(timeout = 10000)
	private EventQueryService eventQueryService;

	/**
	 * 我的足迹 - 用户浏览历史记录
	 * <p>API: /v3/api/behavior/event/find/product
	 * <p> 浏览日期分组，并且当日去重
	 */
	@ResponseBody
	@RequestMapping(value = "/product")
	@SuppressWarnings("rawtypes")
	public ResponseResult findProductEventList(PageModel page) {
		MemberInfo member = getLoginMemberInfo();
		PageResult events = eventQueryService.findProductVisit(member.getId(), page);
		return ResultHandler.successAppPage("products", events);
	}

	/**
	 * 我的足迹 - 用户浏览历史记录总数
	 * <p>API: /v3/api/behavior/event/find/product/count
	 * <p>缓存浏览数据
	 */
	@ResponseBody
	@RequestMapping(value = "/product/count")
	public ResponseResult countProductEvent() {
		MemberInfo member = getLoginMemberInfo();
		Integer count = eventQueryService.countProductEvent(member.getId());
		return ResultHandler.successApp("products", count);
	}

}

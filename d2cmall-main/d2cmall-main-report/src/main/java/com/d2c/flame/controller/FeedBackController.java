package com.d2c.flame.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.d2c.cache.redis.RedisHandler;
import com.d2c.cms.dto.FeedBackDto;
import com.d2c.cms.query.FeedBackSearcher;
import com.d2c.cms.service.FeedBackService;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.api.response.ResponseResult;
import com.d2c.frame.web.control.BaseControl;

@RestController
@RequestMapping("/report/feedback")
public class FeedBackController extends BaseControl {

	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private RedisHandler<String, Map<String, Object>> redisHandler;

	/**
	 * 头部数据
	 * 
	 * @param cookie
	 * @return
	 */
	@RequestMapping(value = "/head", method = RequestMethod.GET)
	public ResponseResult head() {
		ResponseResult result = new ResponseResult();
		result.setData(redisHandler.get("report_feedback"));
		return result;
	}

	/**
	 * 今日反馈
	 * 
	 * @param cookie
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseResult list(PageModel page) {
		ResponseResult result = new ResponseResult();
		FeedBackSearcher searcher = new FeedBackSearcher();
		PageResult<FeedBackDto> pager = feedBackService.findBySearcher(searcher, page);
		result.putPage("pager", pager);
		return result;
	}

}

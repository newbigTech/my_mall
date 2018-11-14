package com.d2c.flame.quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.d2c.cache.redis.RedisHandler;
import com.d2c.cms.query.FeedBackSearcher;
import com.d2c.cms.service.FeedBackService;
import com.d2c.util.date.DateUtil;

@Controller
public class FeedBackTask {

	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private RedisHandler<String, Map<String, Object>> redisHandler;

	private static boolean feedback_executed = true;

	private final static String redis_key = "report_feedback";

	/**
	 * 反馈数据
	 */
	@Scheduled(fixedDelay = 5 * 60 * 1000)
	public void execute() {
		try {
			if (feedback_executed) {

				feedback_executed = false;

				Map<String, Object> map = new HashMap<>();
				Date today = new Date();
				// 本月数据
				Date sMonthDate = DateUtil.getStartOfMonth(today);
				Date eMonthDate = DateUtil.getEndOfMonth(today);
				sMonthDate = DateUtil.getStartOfDay(sMonthDate);
				eMonthDate = DateUtil.getEndOfDay(eMonthDate);

				FeedBackSearcher searcher = new FeedBackSearcher();
				searcher.setStartCreateDate(sMonthDate);
				searcher.setEndCreateDate(eMonthDate);
				map.put("feedbackResult", feedBackService.findCountGroupByType(searcher));

				redisHandler.set(redis_key, map);
			}
		} finally {
			feedback_executed = true;
		}
	}

}

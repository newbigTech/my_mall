package com.d2c.behavior.api.services;

import java.util.List;
import java.util.Map;

import com.d2c.behavior.api.mongo.dto.EventStatDTO;
import com.d2c.behavior.api.mongo.dto.EventStatQueryDTO;
import com.d2c.behavior.api.mongo.dto.EventVisitorDTO;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.core.model.KeyValue;

public interface EventStatService {

	/**
	 * 今天，昨天，总的事件点击人数
	 */
	public EventStatDTO findUvStat(EventStatQueryDTO query);

	/**
	 * 查询几天内的访客数据
	 */
	public List<EventVisitorDTO> findVisitors(EventStatQueryDTO query, PageModel page);


	/**
	 * 查询总图表，根据事件名称分组
	 */
	public Map<String, List<KeyValue>> findEventChartMap(EventStatQueryDTO query);

	/**
	 * 查询点击次数或查询点击人数图表
	 */
	public List<KeyValue> findEventChart(EventStatQueryDTO query);
	
}

package com.d2c.behavior.provider.mongo.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.model.EventTypeDO;
import com.d2c.common.mongodb.base.ListMongoDao;

@Component
public class EventTypeMongoDao extends ListMongoDao<EventTypeDO> {
	
	/**
	 * 获取事件类型
	 */
	public EventTypeDO findByEvent(String event) {
		return this.findOne(new Query(Criteria.where("event").is(event)));
	}

}

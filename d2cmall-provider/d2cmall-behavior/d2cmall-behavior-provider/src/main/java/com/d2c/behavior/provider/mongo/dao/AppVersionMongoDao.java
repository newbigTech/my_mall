package com.d2c.behavior.provider.mongo.dao;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.model.AppVersionDO;
import com.d2c.common.mongodb.base.ListMongoDao;

@Component
public class AppVersionMongoDao extends ListMongoDao<AppVersionDO> {
	
	/**
	 * 查询设备，不存在则新建
	 */
	public AppVersionDO findOneByVersion(String appTerminal, String appVersion){
		return findOne(new Query(Criteria.where("appTerminal").is(appTerminal).and("appVersion").is(appVersion)));
	}
	
}

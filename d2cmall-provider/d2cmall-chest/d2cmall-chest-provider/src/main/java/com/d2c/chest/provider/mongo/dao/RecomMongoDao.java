package com.d2c.chest.provider.mongo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.d2c.chest.provider.mongo.model.RecomDO;
import com.d2c.common.api.query.model.MongoQuery;
import com.d2c.common.mongodb.base.ListMongoDao;

@Component
public class RecomMongoDao extends ListMongoDao<RecomDO> {

	public List<RecomDO> findTopRecom(MongoQuery query, int limit){
		return findQueryPage(null, query, 1, limit, "score", false);
	}
	
}

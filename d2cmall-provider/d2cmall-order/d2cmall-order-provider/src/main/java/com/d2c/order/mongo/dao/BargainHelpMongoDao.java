package com.d2c.order.mongo.dao;

import java.util.Date;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.d2c.common.mongodb.base.ListMongoDao;
import com.d2c.order.mongo.model.BargainHelpDO;

@Component
public class BargainHelpMongoDao extends ListMongoDao<BargainHelpDO> {

	public BargainHelpDO findByUnionId(String unionId) {
		return this.findOne(new Query(Criteria.where("helperUnionId").is(unionId)));
	}

	public int countByUnionId(String unionId, Date begainDate, Date endDate) {
		return (int) this.count(
				new Query(Criteria.where("helperUnionId").is(unionId).and("helpDate").gte(begainDate).lte(endDate)));
	}
}

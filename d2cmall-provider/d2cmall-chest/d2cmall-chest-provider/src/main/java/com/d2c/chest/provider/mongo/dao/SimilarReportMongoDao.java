package com.d2c.chest.provider.mongo.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.d2c.chest.provider.mongo.model.SimilarReportDO;
import com.d2c.common.mongodb.base.BaseMongoDao;
import com.d2c.common.mongodb.enums.ReportStatus;

@Component
public class SimilarReportMongoDao extends BaseMongoDao<SimilarReportDO> {

	public SimilarReportDO findLastReport(Integer schemeId){
		Query query = new Query(Criteria.where("schemeId").is(schemeId)
				.and("status").is(ReportStatus.END.name()));
		query.with(new Sort(Direction.DESC, "gmtCreate"));
		return findOne(query);
	}

	public SimilarReportDO findLastUnDoneReport(Integer schemeId){
		Query query = new Query(Criteria.where("schemeId").is(schemeId)
				.and("status").is(ReportStatus.START.name()));
		query.with(new Sort(Direction.DESC, "gmtCreate"));
		return findOne(query);
	}
	
}

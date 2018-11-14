package com.d2c.analyze.provider.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.d2c.analyze.api.mongo.model.PartnerSaleDO;
import com.d2c.analyze.api.query.base.BasePartnerQuery;
import com.d2c.common.api.page.Pager;
import com.d2c.common.mongodb.base.ListMongoDao;
import com.d2c.member.enums.PartnerStatusEnum;

/**
 * 买手销售数据统计
 * @author wull
 */
@Component
public class PartnerSaleMongoDao extends ListMongoDao<PartnerSaleDO> {

	public List<PartnerSaleDO> findPartnerSaleList(BasePartnerQuery mongoQuery, Pager pager){
		Query query = null;
		if(mongoQuery.getStatus() == null){
			query = new Query(Criteria.where("status").ne(PartnerStatusEnum.CLOSE.getCode()));
		}
		return findQueryPage(query, mongoQuery, pager);
	}

	public long countPartnerSale(BasePartnerQuery mongoQuery){
		Query query = null;
		if(mongoQuery.getStatus() == null){
			query = new Query(Criteria.where("status").ne(PartnerStatusEnum.CLOSE.getCode()));
		}
		return count(query, mongoQuery);
	}

}

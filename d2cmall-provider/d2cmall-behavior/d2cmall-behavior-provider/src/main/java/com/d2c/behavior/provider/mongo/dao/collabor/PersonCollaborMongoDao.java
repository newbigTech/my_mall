package com.d2c.behavior.provider.mongo.dao.collabor;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.model.collabor.PersonCollaborDO;
import com.d2c.common.mongodb.base.ListMongoDao;


/**
 * 用户协同过滤
 * @author wull
 */
@Component
public class PersonCollaborMongoDao extends ListMongoDao<PersonCollaborDO> {
	
	public PersonCollaborDO findCollabor(Long memberId, Long productId){
		return findOne(new Query(Criteria.where("memberId").is(memberId).and("productId").is(productId)));
	}
	
}

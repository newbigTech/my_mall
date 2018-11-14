package com.d2c.behavior.provider.mongo.dao;

import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.model.PersonLocalDO;
import com.d2c.common.mongodb.base.ListMongoDao;

@Component
public class PersonLocalMongoDao extends ListMongoDao<PersonLocalDO> {
	
}

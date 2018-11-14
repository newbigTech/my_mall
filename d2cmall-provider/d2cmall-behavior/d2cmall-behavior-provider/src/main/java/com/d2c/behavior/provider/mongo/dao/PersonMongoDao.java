package com.d2c.behavior.provider.mongo.dao;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.model.PersonDO;
import com.d2c.common.mongodb.base.ListMongoDao;

@Component
public class PersonMongoDao extends ListMongoDao<PersonDO> {

	public PersonDO findByPhone(String phone) {
		return findOne("phone", phone);
	}
	
	public PersonDO findByMemberInfoId(Long memberInfoId) {
		return findOne("memberInfoId", memberInfoId);
	}

	@Override
	public Collection<PersonDO> insert(Collection<PersonDO> list) {
		list.forEach(bean -> {
			save(bean);
		});
		return list;
	}
	
}

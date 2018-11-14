package com.d2c.member.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.d2c.common.mongodb.base.ListMongoDao;
import com.d2c.member.enums.MemberTaskType;
import com.d2c.member.mongo.model.MemberTaskExecDO;

@Component
public class MemberTaskExecMongoDao extends ListMongoDao<MemberTaskExecDO> {

	public MemberTaskExecDO findOne(Long memberId, String taskCode){
		return findOne(new Query(Criteria.where("memberId").is(memberId).and("taskCode").is(taskCode)));
	}

	public List<MemberTaskExecDO> findList(Long memberId){
		return find("memberId", memberId);
	}

	public long updateDayTask(){
		Query query = new Query(Criteria.where("taskType").is(MemberTaskType.DAY_TASK.name()));
		Update update = new Update();
		update.set("count", 0);
		update.set("done", false);
		return update(query, update).getModifiedCount();
	}
	
}

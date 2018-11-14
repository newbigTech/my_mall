package com.d2c.behavior.api.services;

import java.util.List;

import com.d2c.behavior.api.mongo.model.PersonDO;
import com.d2c.behavior.api.mongo.model.PersonThirdDO;
import com.d2c.member.model.Member;
import com.d2c.member.model.MemberInfo;

public interface PersonService {

	/**
	 * 根据用户分组获取用户列表
	 */
	List<PersonDO> findPersonByGroupId(String groupId, Integer page, Integer limit);

	/**
	 * 根据ID查询
	 */
	PersonDO findById(String id);

	/**
	 * 查询并创建第三方登录数据
	 */
	PersonThirdDO findCreatePersonThird(Member member, String personId);

	/**
	 * 查询并保存用户
	 */
	PersonDO findBuildPerson(Long memberId);

	PersonDO findBuildPerson(MemberInfo member);

	/**
	 * 同步用户数据
	 */
	PersonDO applySavePerson(MemberInfo member);

	PersonDO save(PersonDO bean);

}
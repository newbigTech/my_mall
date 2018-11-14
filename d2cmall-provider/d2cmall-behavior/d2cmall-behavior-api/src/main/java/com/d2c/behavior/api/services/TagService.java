package com.d2c.behavior.api.services;

import java.util.List;
import java.util.Map;

import com.d2c.behavior.api.mongo.model.TagTypeDO;

public interface TagService {

	/**
	 * 初始化用户标签定义
	 */
	List<TagTypeDO> initTag();
	
	TagTypeDO save(TagTypeDO type);
	
	Iterable<TagTypeDO> saveAll(Iterable<TagTypeDO> types);

	void cleanAll();
	
	Map<String, List<TagTypeDO>> findTagTypeMap();

	/**
	 * 获取需要处理的用户标签
	 */
	List<TagTypeDO> findTagType(Class<?> inputClz);
	
	List<TagTypeDO> findTagType(String inputClz);

}
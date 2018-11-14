package com.d2c.behavior.provider.mongo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.model.TagTypeDO;
import com.d2c.common.mongodb.base.ListMongoDao;

/**
 * 用户标签类型定义
 * @author wull
 */
@Component
public class TagTypeMongoDao extends ListMongoDao<TagTypeDO> {

	public List<TagTypeDO> findByInputClz(String inputClz){
		return find("inputClz", inputClz);
	}

}

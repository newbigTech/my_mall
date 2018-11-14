package com.d2c.behavior.provider.mongo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.model.TagDO;
import com.d2c.common.mongodb.base.ListMongoDao;

/**
 * 用户标签定义
 * @author wull
 */
@Component
public class TagMongoDao extends ListMongoDao<TagDO> {
	
	public List<TagDO> findByTypeId(String typeId){
		return find("typeId", typeId);
	}

}

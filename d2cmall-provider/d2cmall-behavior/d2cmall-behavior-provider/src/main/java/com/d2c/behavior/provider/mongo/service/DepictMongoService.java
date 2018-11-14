package com.d2c.behavior.provider.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.model.depict.DepictDO;
import com.d2c.behavior.provider.mongo.dao.depict.DepictMongoDao;
import com.d2c.behavior.provider.mongo.dao.depict.DepictTagMongoDao;

/**
 * 用户画像规则
 * @author wull
 */
@Component
public class DepictMongoService {

	@Autowired
	private DepictMongoDao depictMongoDao;
	@Autowired
	private DepictTagMongoDao depictTagMongoDao;
	
	/**
	 * 清除
	 */
	public void cleanAll(){
		depictMongoDao.cleanAll();
		depictTagMongoDao.cleanAll();
	}

	/**
	 * 修改并保存
	 */
	public DepictDO save(DepictDO depict) {
		if(depict.getDepictTags() != null){
			depict.getDepictTags().forEach((k, tag) -> {
				depictTagMongoDao.save(tag);
			});
		}
		return depictMongoDao.save(depict);
	}
	
	public List<DepictDO> findPage(Integer page, Integer limit){
		return depictMongoDao.findPage(page, limit);
	}
	

}

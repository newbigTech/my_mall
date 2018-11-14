package com.d2c.behavior.provider.mongo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.model.EventDO;
import com.d2c.behavior.provider.mongo.dao.EventMongoDao;

/**
 * 埋点事件
 * @author wull
 */
@Component
public class EventMongoService {

	@Autowired
	private EventMongoDao eventMongoDao;
	@Autowired
	private EventTypeMongeService eventTypeMongeService;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 保存埋点事件
	 * <p>根据埋点字符自动创建埋点事件类型
	 */
	public EventDO saveEvent(EventDO event){
		eventTypeMongeService.findBuildEventType(event);
		return eventMongoDao.save(event);
	}
	
	/**
	 * 获得session最后一条事件
	 */
	public EventDO getLastBySessionId(String sessionId) {
		return eventMongoDao.getLastBySessionId(sessionId);
	}
	
	public void updateUnShow(String id, Boolean unShow) {
		eventMongoDao.updateUnShow(id, unShow);
	}
	
	
}

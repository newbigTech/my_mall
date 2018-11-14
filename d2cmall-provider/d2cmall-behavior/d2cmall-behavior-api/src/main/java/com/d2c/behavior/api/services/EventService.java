package com.d2c.behavior.api.services;

import java.util.List;

import com.d2c.behavior.api.mongo.dto.EventDTO;
import com.d2c.behavior.api.mongo.model.EventDO;
import com.d2c.behavior.api.mongo.model.PersonSessionDO;

public interface EventService {

	/**
	 * 用户行为埋点
	 */
	public EventDO event(PersonSessionDO session, EventDTO dto, EventDO last);
	
	public List<EventDO> event(PersonSessionDO session, List<EventDTO> list, EventDO last);
	
	public void updateUnShow(String id, Boolean unShow);

}

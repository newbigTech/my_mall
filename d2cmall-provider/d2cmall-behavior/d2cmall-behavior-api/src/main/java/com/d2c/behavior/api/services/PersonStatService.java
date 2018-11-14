package com.d2c.behavior.api.services;

import java.util.List;

import com.d2c.behavior.api.mongo.dto.PersonSessionDTO;

public interface PersonStatService {
	
	public List<PersonSessionDTO> findPersonSessionList();
	
	public List<PersonSessionDTO> findVistorSessionList();
	
}

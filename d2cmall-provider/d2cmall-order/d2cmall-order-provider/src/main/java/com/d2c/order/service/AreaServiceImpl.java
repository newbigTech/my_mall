package com.d2c.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.mybatis.service.ListServiceImpl;
import com.d2c.order.dao.AreaMapper;
import com.d2c.order.model.Area;

@Service("areaService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class AreaServiceImpl extends ListServiceImpl<Area> implements AreaService {

	@Autowired
	private AreaMapper areaMapper;

	public List<Area> findAreaByName(String name) {
		return areaMapper.findAreaByName(name);
	}

	@Override
	public Area findAreaByCode(Integer code) {
		return areaMapper.findAreaByCode(code.toString());
	}

}

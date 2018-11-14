package com.d2c.common.api.service;

import java.util.List;

import com.d2c.common.api.dto.RelateDTO;

public interface RelateService<T, E, F> extends BaseService<T> {

	public List<F> getSlaveListByMasterId(Integer id);

	public void saveRelate(RelateDTO<T> relateList);

}

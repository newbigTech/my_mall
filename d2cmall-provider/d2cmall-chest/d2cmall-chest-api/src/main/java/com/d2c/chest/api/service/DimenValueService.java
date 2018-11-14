package com.d2c.chest.api.service;


import java.util.List;

import com.d2c.chest.api.entity.DimenValueDO;
import com.d2c.common.api.service.ListService;

/**
 * 商品属性维度
 * @author wull
 */
public interface DimenValueService extends ListService<DimenValueDO> {

	public List<DimenValueDO> findDimenValueByKeyId(Integer keyId);
	
	public List<Double> getDistByValue(Integer ruleId, String fieldValue);
	
	public DimenValueDO createDimenValue(Integer keyId, Integer tplId, Double dist);
	
}

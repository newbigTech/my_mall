package com.d2c.chest.api.service;


import java.util.List;

import com.d2c.chest.api.entity.DimenTplDO;
import com.d2c.common.api.service.ListService;

/**
 * 商品属性维度
 * @author wull
 */
public interface DimenTplService extends ListService<DimenTplDO> {

	public List<DimenTplDO> findDimenTplByRuleCode(String ruleCode);
	
}

package com.d2c.chest.api.service;


import java.util.List;

import com.d2c.chest.api.entity.DimenKeyDO;
import com.d2c.common.api.service.ListService;

/**
 * 商品属性维度
 * @author wull
 */
public interface DimenKeyService extends ListService<DimenKeyDO> {

	public List<DimenKeyDO> findDimenKeys(Integer ruleId);

	public DimenKeyDO createDimenKey(Integer ruleId, String fieldName, String fieldValue);
	
}

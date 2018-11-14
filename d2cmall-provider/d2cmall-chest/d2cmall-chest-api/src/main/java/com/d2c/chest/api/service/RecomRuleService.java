package com.d2c.chest.api.service;


import java.util.List;

import com.d2c.chest.api.entity.RecomRuleDO;
import com.d2c.common.api.service.ListService;

/**
 * 推荐规则表
 * @author wull
 */
public interface RecomRuleService extends ListService<RecomRuleDO>  {
	
	public List<RecomRuleDO> findAllRules();

	public List<RecomRuleDO> rebuildRules();
    
}

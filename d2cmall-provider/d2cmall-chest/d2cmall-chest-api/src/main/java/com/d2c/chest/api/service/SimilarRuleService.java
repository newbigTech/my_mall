package com.d2c.chest.api.service;


import java.util.List;

import com.d2c.chest.api.dto.SimilarRuleDTO;
import com.d2c.chest.api.entity.SimilarRuleDO;
import com.d2c.common.api.service.ListService;

/**
 * 相似度规则
 * @author wull
 */
public interface SimilarRuleService extends ListService<SimilarRuleDO> {
	
	public SimilarRuleDTO getSimilarRuleOnEdit(Integer ruleId);
	
	public List<SimilarRuleDO> createRuleBySchemeId(Integer schemeId);
	
	public SimilarRuleDO findRuleByCode(Integer schemeId, String ruleCode);
	
	public List<SimilarRuleDO> findRulesBySchemeId(Integer schemeId);
	
}

package com.d2c.chest.provider.dao;

import org.apache.ibatis.annotations.Param;

import com.d2c.chest.api.entity.SimilarRuleDO;
import com.d2c.mybatis.mapper.SuperMapper;

/**
 * @author wull
 */
public interface SimilarRuleMapper extends SuperMapper<SimilarRuleDO> {

	public SimilarRuleDO findRuleByCode(@Param("schemeId") Integer schemeId, @Param("ruleCode") String ruleCode);
	
}

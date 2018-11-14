package com.d2c.chest.provider.similar.resolver;

import org.springframework.stereotype.Component;

import com.d2c.chest.api.entity.SimilarRuleDO;
import com.d2c.chest.provider.similar.resolver.enums.SimilarRuleType;
import com.d2c.common.core.base.resolver.BaseResolverFactory;

@Component
public class SimilarResolverFactory extends BaseResolverFactory<SimilarResolver, SimilarRuleDO>  {

	@Override
	public SimilarResolver initResolver(SimilarResolver prev, SimilarRuleDO rule) {
		return SimilarRuleType.createResolver(rule);
	}

}

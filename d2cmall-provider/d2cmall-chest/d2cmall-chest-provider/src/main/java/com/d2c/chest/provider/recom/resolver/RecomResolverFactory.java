package com.d2c.chest.provider.recom.resolver;

import org.springframework.stereotype.Component;

import com.d2c.chest.api.entity.RecomRuleDO;
import com.d2c.common.core.base.resolver.BaseResolverFactory;


@Component
public class RecomResolverFactory extends BaseResolverFactory<RecomResolver, RecomRuleDO>  {

	@Override
	public RecomResolver initResolver(RecomResolver prev, RecomRuleDO rule) {
		return new RecomResolver();
	}
	
}

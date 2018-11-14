package com.d2c.flame.freemarker;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.d2c.cms.dto.NavigationDto;
import com.d2c.cms.service.NavigationService;
import com.d2c.common.core.cache.old.CacheCallback;
import com.d2c.common.core.cache.old.CacheKey;
import com.d2c.common.core.cache.old.CacheTimerHandler;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class RenderNavigation implements TemplateMethodModelEx {

	protected static final Log logger = LogFactory.getLog(RenderNavigation.class);

	@Autowired
	private NavigationService navigationService;

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List arg0) throws TemplateModelException {
		String key = CacheKey.NAVIGATIONKEY;
		List<NavigationDto> list = CacheTimerHandler.getAndSetCacheValue(key, 10,
				new CacheCallback<List<NavigationDto>>() {
					@Override
					public List<NavigationDto> doExecute() {
						List<NavigationDto> navigations = navigationService.getIndexNavigation(1, "v2");
						return navigations;
					}
				});
		return list;
	}

}

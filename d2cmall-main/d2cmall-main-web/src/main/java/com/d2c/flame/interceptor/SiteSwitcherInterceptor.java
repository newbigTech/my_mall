package com.d2c.flame.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.site.SitePreferenceHandler;
import org.springframework.mobile.device.switcher.SiteSwitcherHandlerInterceptor;
import org.springframework.mobile.device.switcher.SiteUrlFactory;

public class SiteSwitcherInterceptor extends SiteSwitcherHandlerInterceptor {

	public SiteSwitcherInterceptor(SiteUrlFactory normalSiteUrlFactory, SiteUrlFactory mobileSiteUrlFactory,
			SitePreferenceHandler sitePreferenceHandler) {
		super(normalSiteUrlFactory, mobileSiteUrlFactory, sitePreferenceHandler);
	}

	public static SiteSwitcherHandlerInterceptor mDot(String serverName, int doReplace) {
		if (doReplace == 1) {
			return standard(serverName, serverName.replace("www.", "m."), serverName, false);
		} else {
			return new SiteSwitcherInterceptor(null, null, null);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

}

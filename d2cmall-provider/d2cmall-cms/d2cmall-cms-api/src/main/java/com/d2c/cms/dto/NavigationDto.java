package com.d2c.cms.dto;

import java.util.List;

import com.d2c.cms.model.Navigation;
import com.d2c.cms.model.NavigationItem;

public class NavigationDto extends Navigation {

	private static final long serialVersionUID = 1L;

	private List<NavigationDto> children;

	private List<NavigationItem> navigationItems;

	public List<NavigationDto> getChildren() {
		return children;
	}

	public void setChildren(List<NavigationDto> children) {
		this.children = children;
	}

	public List<NavigationItem> getNavigationItems() {
		return navigationItems;
	}

	public void setNavigationItems(List<NavigationItem> navigationItems) {
		this.navigationItems = navigationItems;
	}

}

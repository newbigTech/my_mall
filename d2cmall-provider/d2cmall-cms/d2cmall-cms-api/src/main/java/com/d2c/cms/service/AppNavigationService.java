package com.d2c.cms.service;

import java.util.List;

import com.d2c.cms.model.AppNavigation;
import com.d2c.cms.query.AppNavigationSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

/**
 * app底部导航栏
 * 
 * @author Administrator
 *
 */
public interface AppNavigationService {
	/**
	 * 新增
	 * 
	 * @param appNavigation
	 * @return
	 */
	AppNavigation insert(AppNavigation appNavigation);

	/**
	 * 更新
	 * 
	 * @param appNavigation
	 * @return
	 */
	int update(AppNavigation appNavigation);

	/**
	 * 更新状态
	 * 
	 * @param id
	 * @param status
	 * @param operator
	 * @return
	 */
	int updateStatus(Long id, Integer status, String operator);

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	AppNavigation findById(Long id);

	/**
	 * 根据条件查找
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	PageResult<AppNavigation> findBySearcher(AppNavigationSearcher searcher, PageModel page);

	/**
	 * 查找所有上架的
	 * 
	 * @return
	 */
	List<AppNavigation> findAllEnable();
}

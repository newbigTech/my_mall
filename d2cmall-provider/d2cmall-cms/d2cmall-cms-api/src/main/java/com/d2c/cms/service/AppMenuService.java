
package com.d2c.cms.service;

import java.util.List;

import com.d2c.cms.model.AppMenu;
import com.d2c.cms.query.AppMenuSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface AppMenuService {

	/**
	 * 分页查找
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	PageResult<AppMenu> findBySearcher(AppMenuSearcher searcher, PageModel page);

	/**
	 * 根据状态查找
	 * 
	 * @param status
	 * @return
	 */
	List<AppMenu> findByStatus(Integer status, String version);

	/**
	 * 插入按钮定义
	 * 
	 * @param appMenu
	 * @return
	 */
	AppMenu insert(AppMenu appMenu);

	/**
	 * 更新
	 * 
	 * @param appMenu
	 * @return
	 */
	int update(AppMenu appMenu);

	/**
	 * 通过id更新状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	int updateStatusById(Long id, Integer status, String name);

	/**
	 * 更新排序
	 * 
	 * @param id
	 * @param sort
	 * @return
	 */
	int updateSortById(Long id, Integer sort);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

}

package com.d2c.msg.service;

import java.util.List;
import java.util.Map;

import com.d2c.msg.model.UserOperateLog;
import com.d2c.msg.query.UserOperateLogSearcher;

/**
 * 会员操作日志（useroperatelog）
 *
 */
public interface UserOperateLogService {

	/**
	 * 根据UserOperateLogSearcher对象内的过滤条件，按照操作对象ID分组统计数量
	 * 
	 * @param searcher
	 *            过滤器
	 * @return
	 */
	List<Map<String, Object>> count(UserOperateLogSearcher searcher);

	/**
	 * 保存会员操作日志
	 * 
	 * @param entity
	 * @return
	 */
	int insert(List<UserOperateLog> entity, boolean file);

	/**
	 * 根据id获取会员操作日志
	 * 
	 * @param id
	 * @return
	 */
	UserOperateLog findById(Long id);

}

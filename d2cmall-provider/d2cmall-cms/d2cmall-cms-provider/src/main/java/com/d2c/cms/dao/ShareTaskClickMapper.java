package com.d2c.cms.dao;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.ShareTaskClick;
import com.d2c.mybatis.mapper.SuperMapper;

/**
 * 
 * @author xuhua
 * @see 分享点击
 */
public interface ShareTaskClickMapper extends SuperMapper<ShareTaskClick> {

	/**
	 * 获取点击数，IP相同，排除
	 */
	int countBy(@Param("shareTaskId") Long shareTaskId, @Param("ip") String ip);

	/**
	 * 更新为已统计
	 */
	int doCounted(Long id);

}

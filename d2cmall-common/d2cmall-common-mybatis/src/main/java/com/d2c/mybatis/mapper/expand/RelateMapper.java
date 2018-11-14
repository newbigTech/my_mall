package com.d2c.mybatis.mapper.expand;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.mybatis.mapper.expand.provider.RelateProvider;

/**
 * 扩展关联对象接口
 * @author wull
 */
public interface RelateMapper<T, E, F> extends SuperMapper<T> {


	/**
	 * 根据主表ID获取从属对象列表
	 */
	@SelectProvider(type = RelateProvider.class, method = "dynamicSQL")
	List<F> getSlaveListByMasterId(@Param("masterId") Integer masterId);
	
}

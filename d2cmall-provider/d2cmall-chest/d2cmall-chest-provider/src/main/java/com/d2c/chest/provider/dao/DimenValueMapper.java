package com.d2c.chest.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.chest.api.entity.DimenValueDO;
import com.d2c.mybatis.mapper.SuperMapper;

/**
 * @author wull
 */
public interface DimenValueMapper extends SuperMapper<DimenValueDO> {

	public List<Double> getDistByValue(@Param("ruleId") Integer ruleId, @Param("fieldValue") String fieldValue);
	
}

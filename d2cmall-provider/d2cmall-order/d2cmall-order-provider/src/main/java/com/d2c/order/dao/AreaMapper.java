package com.d2c.order.dao;

import java.util.List;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.Area;

/**
 * @see 地区管理
 * @author xh
 *
 */
public interface AreaMapper extends SuperMapper<Area> {

	List<Area> findAreaByName(String name);

	Area findAreaByCode(String code);

}

package com.d2c.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.Logistics;
import com.d2c.order.model.LogisticsCompany;

public interface LogisticsMapper extends SuperMapper<Logistics> {

	int update(Logistics logostics);

	Logistics findBySnAndCom(@Param("sn") String sn, @Param("com") String com, @Param("type") String type);

	LogisticsCompany findCompanyByName(@Param("name") String name);

	List<Logistics> findAllBySn(@Param("sn") String sn, @Param("com") String com);
}

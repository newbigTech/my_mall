package com.d2c.analyze.provider.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.d2c.analyze.api.mongo.dto.SaleStatDTO;
import com.d2c.mybatis.mapper.BaseMapper;

public interface PartnerSaleMapper extends BaseMapper {

	int countInvite(@Param("partnerId") Long partnerId, @Param("level") Integer level, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
	
	SaleStatDTO findSaleStat(@Param("fieldName") String fieldName, @Param("fieldValue") Object fieldValue, 
			@Param("startTime") Date startTime, @Param("endTime") Date endTime);

}

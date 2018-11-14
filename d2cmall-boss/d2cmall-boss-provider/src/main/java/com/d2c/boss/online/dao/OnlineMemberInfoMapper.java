package com.d2c.boss.online.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.online.model.OnlineMemberInfo;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OnlineMemberInfoMapper extends SuperMapper<OnlineMemberInfo> {

	Date getLastSysDate();

	int updateStatus(@Param("lastSysDate") Date lastSysDate);
}

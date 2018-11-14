package com.d2c.boss.online.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.online.model.OnlineMember;
import com.d2c.mybatis.mapper.SuperMapper;

public interface OnlineMemberMapper extends SuperMapper<OnlineMember> {

	Date getLastSysDate();

	int updateStatus(@Param("lastSysDate") Date lastSysDate);
}

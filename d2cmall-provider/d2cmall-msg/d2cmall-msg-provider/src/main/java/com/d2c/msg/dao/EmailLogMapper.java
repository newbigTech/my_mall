package com.d2c.msg.dao;

import org.apache.ibatis.annotations.Param;

import com.d2c.msg.model.EmailLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface EmailLogMapper extends SuperMapper<EmailLog> {

	int updateSend(Long id);

	EmailLog findBySourceIdAndType(@Param("sourceId") Long sourceId, @Param("type") String type);
}

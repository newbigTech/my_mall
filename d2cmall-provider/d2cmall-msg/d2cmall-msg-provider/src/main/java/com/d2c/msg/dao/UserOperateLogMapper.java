package com.d2c.msg.dao;

import java.util.List;
import java.util.Map;

import com.d2c.msg.model.UserOperateLog;
import com.d2c.msg.query.UserOperateLogSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface UserOperateLogMapper extends SuperMapper<UserOperateLog> {

	List<Map<String, Object>> count(UserOperateLogSearcher searcher);

}

package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.SmsLog;
import com.d2c.msg.model.SmsLog.SmsLogType;
import com.d2c.msg.query.SmsLogSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface SmsLogMapper extends SuperMapper<SmsLog> {

	SmsLog findBySourceAndType(@Param("source") String source, @Param("type") SmsLogType type);

	SmsLog findOneByIp(@Param("ip") String source, @Param("type") String type);

	int deleteExpireLog();

	int countByMobileAndTime(@Param("mobile") String mobile, @Param("intervalTime") long intervalTime);

	int countBySearcher(@Param("searcher") SmsLogSearcher searcher);

	List<SmsLog> findBySearcher(@Param("searcher") SmsLogSearcher searcher, @Param("pager") PageModel page);

}

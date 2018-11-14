package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.AccountLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface AccountLogMapper extends SuperMapper<AccountLog> {

	List<AccountLog> findByAccountId(@Param("accountId") Long accountId, @Param("page") PageModel page);

}

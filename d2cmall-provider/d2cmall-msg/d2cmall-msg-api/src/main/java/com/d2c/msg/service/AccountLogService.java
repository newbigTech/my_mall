package com.d2c.msg.service;

import java.util.List;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.AccountLog;

public interface AccountLogService {

	/**
	 * 添加一天账户日志记录
	 * 
	 * @param accountLog
	 * @return
	 */
	AccountLog insert(AccountLog accountLog);

	List<AccountLog> findByAccountId(Long accountId, PageModel page);

}

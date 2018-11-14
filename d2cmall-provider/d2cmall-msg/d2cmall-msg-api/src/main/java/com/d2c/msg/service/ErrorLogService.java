package com.d2c.msg.service;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.msg.model.ErrorLog;
import com.d2c.msg.query.ErrorLogSearcher;

public interface ErrorLogService {

	ErrorLog insert(ErrorLog errorLog);

	PageResult<ErrorLog> findBySearcher(PageModel pager, ErrorLogSearcher searcher);

	int delete(Long id);

}

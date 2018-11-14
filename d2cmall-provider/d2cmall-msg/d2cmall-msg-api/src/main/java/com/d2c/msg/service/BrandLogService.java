package com.d2c.msg.service;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.msg.model.BrandLog;

public interface BrandLogService {

	BrandLog insert(BrandLog log);

	PageResult<BrandLog> findByDesignerId(Long designerId, PageModel page);

}

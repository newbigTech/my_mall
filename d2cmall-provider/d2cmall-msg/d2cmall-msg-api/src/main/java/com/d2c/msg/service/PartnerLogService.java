package com.d2c.msg.service;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.msg.model.PartnerLog;

public interface PartnerLogService {

	PartnerLog insert(PartnerLog log);

	PageResult<PartnerLog> findByPartnerId(Long partnerId, PageModel page);

}

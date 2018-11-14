package com.d2c.msg.service;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.msg.model.MagazineLog;

public interface MagazineLogService {

	int insert(MagazineLog log);

	PageResult<MagazineLog> findByMemberId(Long memberId, PageModel page);

}

package com.d2c.boss.member.service;

import com.d2c.boss.member.model.Behavior;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface BehaviorService {

	PageResult<Behavior> findBehaviorsByQuery(PageModel page, ProQuery query);

	Behavior insert(Behavior behavior);

}

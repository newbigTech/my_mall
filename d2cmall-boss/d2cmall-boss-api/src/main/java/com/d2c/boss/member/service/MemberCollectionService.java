package com.d2c.boss.member.service;

import com.d2c.boss.member.model.MemberCollection;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface MemberCollectionService {

	PageResult<MemberCollection> findMemberCollectionsByQuery(PageModel page, ProQuery query);

	MemberCollection insert(MemberCollection memberCollection);

}

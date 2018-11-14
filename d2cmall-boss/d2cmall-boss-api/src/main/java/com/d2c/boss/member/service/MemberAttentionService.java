package com.d2c.boss.member.service;

import com.d2c.boss.member.model.MemberAttention;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface MemberAttentionService {

	PageResult<MemberAttention> findMemberAttentionsByQuery(PageModel page, ProQuery query);

	MemberAttention insert(MemberAttention memberAttention);
}

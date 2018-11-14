package com.d2c.boss.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.member.model.MemberAttention;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MemberAttentionMapper extends SuperMapper<MemberAttention> {

	int countMemberAttentionsByQuery(@Param("query") ProQuery query);

	List<MemberAttention> findMemberAttentionsByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

}

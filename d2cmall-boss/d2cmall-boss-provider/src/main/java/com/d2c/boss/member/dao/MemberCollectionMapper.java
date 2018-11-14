package com.d2c.boss.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.member.model.MemberCollection;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MemberCollectionMapper extends SuperMapper<MemberCollection> {

	int countMemberCollectionsByQuery(@Param("query") ProQuery query);

	List<MemberCollection> findMemberCollectionsByQuery(@Param("pager") PageModel pager,
			@Param("query") ProQuery query);

}

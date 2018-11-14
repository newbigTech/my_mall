package com.d2c.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.member.model.MemberShareTag;
import com.d2c.member.query.MemberShareTagSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MemberShareTagMapper extends SuperMapper<MemberShareTag> {

	List<MemberShareTag> findByMemberShareId(Long shareId);

	MemberShareTag findByCode(@Param("code") String code);

	List<MemberShareTag> findAll();

	List<MemberShareTag> findBySearch(@Param("searcher") MemberShareTagSearcher searcher,
			@Param("pager") PageModel page);

	int countBySearch(@Param("searcher") MemberShareTagSearcher searcher);

	int updateSort(@Param("id") Long id, @Param("sort") Integer sort);

	int updateStatus(@Param("id") Long id, @Param("status") Integer status);

}

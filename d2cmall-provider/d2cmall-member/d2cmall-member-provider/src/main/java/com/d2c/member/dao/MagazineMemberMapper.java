package com.d2c.member.dao;

import org.apache.ibatis.annotations.Param;

import com.d2c.member.model.MagazineMember;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MagazineMemberMapper extends SuperMapper<MagazineMember> {

	MagazineMember findOne(@Param("memberId") Long memberId, @Param("code") String code);

}

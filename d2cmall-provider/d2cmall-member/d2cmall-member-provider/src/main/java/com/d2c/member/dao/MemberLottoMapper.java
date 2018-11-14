package com.d2c.member.dao;

import org.apache.ibatis.annotations.Param;

import com.d2c.member.model.MemberLotto;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MemberLottoMapper extends SuperMapper<MemberLotto> {

	int updateMemberLotteryAddCount(@Param("id") Long id, @Param("json") String json,
			@Param("addCount") Integer addCount);

	int doDecreaseOpportunity(@Param("memberId") Long memberId, @Param("sessionId") Long sessionId);

	MemberLotto findByMemberIdAndSessionId(@Param("memberId") Long memberId, @Param("sessionId") Long sessionId);

}

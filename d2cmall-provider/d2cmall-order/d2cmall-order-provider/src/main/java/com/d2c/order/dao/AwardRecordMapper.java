package com.d2c.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.AwardRecord;
import com.d2c.order.query.AwardRecordSearcher;

public interface AwardRecordMapper extends SuperMapper<AwardRecord> {

	List<AwardRecord> findListByRecently(Long sessionId);

	List<AwardRecord> findBySearcher(@Param("searcher") AwardRecordSearcher searcher, @Param("pager") PageModel page);

	int countBySearcher(@Param("searcher") AwardRecordSearcher searcher);

	List<AwardRecord> findByMemberIdAndSessionId(@Param("memberId") Long memberId, @Param("sessionId") Long sessionId);

	List<String> findAwardLevelName();

}

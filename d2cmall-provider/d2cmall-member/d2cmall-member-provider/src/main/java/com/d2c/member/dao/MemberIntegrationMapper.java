package com.d2c.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.member.model.MemberIntegration;
import com.d2c.member.query.MemberIntegrationSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MemberIntegrationMapper extends SuperMapper<MemberIntegration> {

	MemberIntegration findByTransctionAndType(@Param("transactionId") Long transactionId, @Param("type") String type);

	int countBySearcher(@Param("searcher") MemberIntegrationSearcher searcher);

	List<MemberIntegration> findBySearcher(@Param("searcher") MemberIntegrationSearcher searcher,
			@Param("page") PageModel page);

	int updateRemark(@Param("id") Long id, @Param("remark") String remark);

}

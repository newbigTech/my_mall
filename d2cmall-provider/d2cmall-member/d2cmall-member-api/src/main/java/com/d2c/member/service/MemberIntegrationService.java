package com.d2c.member.service;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.model.MemberIntegration;
import com.d2c.member.model.MemberTask;
import com.d2c.member.query.MemberIntegrationSearcher;

public interface MemberIntegrationService {

	/**
	 * 根据transctionId和type查询
	 * 
	 * @param commentId
	 * @param name
	 * @return
	 */
	MemberIntegration findByTransctionAndType(Long transactionId, String type);

	/**
	 * 分页查询
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	PageResult<MemberIntegration> findBySearch(MemberIntegrationSearcher searcher, PageModel page);

	/**
	 * 按条件统计
	 * 
	 * @param searcher
	 * @return
	 */
	int countBySearcher(MemberIntegrationSearcher searcher);

	/**
	 * 插入
	 * 
	 * @param memberIntegration
	 * @return
	 */
	MemberIntegration insert(MemberIntegration memberIntegration);

	/**
	 * 积分变换
	 * 
	 * @param memberIntegration
	 * @return
	 */
	int doSendIntegration(MemberIntegration memberIntegration);

	/**
	 * 用户日常任务增加积分
	 */
	MemberIntegration addTaskPoint(MemberInfo memberInfo, MemberTask task);

	/**
	 * 更新备注
	 * 
	 * @param id
	 * @param remark
	 * @return
	 */
	int updateRemark(Long id, String remark);

}

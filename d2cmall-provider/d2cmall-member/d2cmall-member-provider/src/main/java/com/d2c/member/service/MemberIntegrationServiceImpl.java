package com.d2c.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.base.utils.AssertUt;
import com.d2c.member.dao.MemberIntegrationMapper;
import com.d2c.member.enums.PointRuleTypeEnum;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.model.MemberIntegration;
import com.d2c.member.model.MemberTask;
import com.d2c.member.query.MemberIntegrationSearcher;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("memberIntegrationService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class MemberIntegrationServiceImpl extends ListServiceImpl<MemberIntegration>
		implements MemberIntegrationService {

	@Autowired
	private MemberIntegrationMapper memberIntegrationMapper;
	@Autowired
	private MemberDetailService memberDetailService;
	@Autowired
	private MemberInfoService memberInfoService;

	@Override
	public MemberIntegration findByTransctionAndType(Long transactionId, String type) {
		return memberIntegrationMapper.findByTransctionAndType(transactionId, type);
	}

	@Override
	public PageResult<MemberIntegration> findBySearch(MemberIntegrationSearcher searcher, PageModel page) {
		PageResult<MemberIntegration> pager = new PageResult<>(page);
		int totalCount = memberIntegrationMapper.countBySearcher(searcher);
		if (totalCount > 0) {
			List<MemberIntegration> list = memberIntegrationMapper.findBySearcher(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public int countBySearcher(MemberIntegrationSearcher searcher) {
		return memberIntegrationMapper.countBySearcher(searcher);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public MemberIntegration insert(MemberIntegration memberIntegration) {
		return this.save(memberIntegration);
	}

	@Override
	@TxTransaction
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int doSendIntegration(MemberIntegration memberIntegration) {
		int success = 0;
		MemberInfo memberInfo = memberInfoService.findById(memberIntegration.getMemberId());
		memberIntegration.setLoginCode(memberInfo.getLoginCode());
		MemberIntegration newIntegration = null;
		// 除了兑换，其他业务只有一次
		if (!PointRuleTypeEnum.EXCHANGE.name().equals(memberIntegration.getType())) {
			newIntegration = this.findByTransctionAndType(memberIntegration.getTransactionId(),
					memberIntegration.getType());
		}
		if (newIntegration == null) {
			memberIntegration = this.insert(memberIntegration);
			success = memberDetailService.updateIntegration(memberIntegration.getMemberId(),
					memberIntegration.getPoint() * memberIntegration.getDirection());
		}
		return success;
	}

	/**
	 * 用户日常任务增加积分
	 */
	@TxTransaction
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public MemberIntegration addTaskPoint(MemberInfo memberInfo, MemberTask task) {
		AssertUt.notNull(memberInfo);
		AssertUt.notNull(task);
		MemberIntegration bean = new MemberIntegration(memberInfo, PointRuleTypeEnum.TASK, task.getPoint(), true);
		bean.setTransactionId(task.getId());
		bean.setTransactionSn(task.getCode());
		bean.setTransactionTime(new Date());
		bean.setTransactionInfo("用户完成任务:" + task.getName());
		
		this.saveIntegration(bean);
		return bean;
	}

	private MemberIntegration saveIntegration(MemberIntegration bean) {
		this.insert(bean);
		memberDetailService.updateIntegration(bean.getMemberId(), bean.getPoint() * bean.getDirection());
		return bean;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int updateRemark(Long id, String remark) {
		return memberIntegrationMapper.updateRemark(id, remark);
	}

}

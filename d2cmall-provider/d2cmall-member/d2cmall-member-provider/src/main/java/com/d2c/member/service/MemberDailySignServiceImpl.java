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
import com.d2c.common.base.exception.BusinessException;
import com.d2c.member.dao.MemberDailySignMapper;
import com.d2c.member.enums.PointRuleTypeEnum;
import com.d2c.member.enums.SignRewardEnum;
import com.d2c.member.model.MemberDailySign;
import com.d2c.member.model.MemberIntegration;
import com.d2c.member.model.MemberIntegration.StatusEnum;
import com.d2c.member.query.MemberDailySignSearcher;
import com.d2c.mybatis.service.ListServiceImpl;
import com.d2c.util.date.DateUtil;

@Service(value = "memberDailySignService")
@TxTransaction
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MemberDailySignServiceImpl extends ListServiceImpl<MemberDailySign> implements MemberDailySignService {

	@Autowired
	private MemberDailySignMapper memberDailySignMapper;
	@Autowired
	private MemberIntegrationService memberIntegrationService;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public MemberDailySign doSign(Long memberId, String loginCode) {
		int signDay = 1;
		MemberDailySignSearcher searcher = new MemberDailySignSearcher();
		searcher.setMemberId(memberId);
		MemberDailySign memberDailySign = memberDailySignMapper.findLastByMember(memberId);
		if (memberDailySign != null) {
			if (memberDailySign.getCreateDate().compareTo(DateUtil.getStartOfDay(new Date())) > 0) {
				throw new BusinessException("您今日已签过到了，明日再来吧");
			}
			// 未满7天且连续签到的，则累加
			if (memberDailySign.getTotalDay() < SignRewardEnum.SEVENTH.getDay() && memberDailySign.getCreateDate()
					.compareTo(DateUtil.getStartOfDay(DateUtil.getIntervalDay(new Date(), -1))) >= 0) {
				signDay = memberDailySign.getTotalDay() + 1;
			}
		}

		int point = SignRewardEnum.getRewardByDay(signDay);
		MemberDailySign newDailySign = new MemberDailySign(memberId, loginCode, signDay, point,
				memberDailySign == null ? point : point + memberDailySign.getTotalPoint());
		// 送D币
		newDailySign = this.save(newDailySign);
		if (newDailySign.getId() > 0) {
			MemberIntegration memberIntegration = new MemberIntegration(memberId, loginCode,
					PointRuleTypeEnum.SING.name(), newDailySign.getPoint(), newDailySign.getId(),
					String.valueOf(System.currentTimeMillis()), newDailySign.getCreateDate(), "签到领D币", 1,
					StatusEnum.SUCCESS.getCode());
			int success = memberIntegrationService.doSendIntegration(memberIntegration);
			if (success < 1) {
				throw new BusinessException("签到失败");
			}
		}
		return newDailySign;
	}

	@Override
	public PageResult<MemberDailySign> findBySearcher(MemberDailySignSearcher searcher, PageModel page) {
		PageResult<MemberDailySign> pager = new PageResult<>(page);
		int totalCount = memberDailySignMapper.countBySearcher(searcher);
		if (totalCount > 0) {
			List<MemberDailySign> list = memberDailySignMapper.findBySearcher(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public MemberDailySign findLastByMember(Long memberId) {
		return memberDailySignMapper.findLastByMember(memberId);
	}

}

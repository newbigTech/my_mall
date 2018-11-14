package com.d2c.boss.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.member.dao.MemberAttentionMapper;
import com.d2c.boss.member.model.MemberAttention;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("memberAttentionService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MemberAttentionServiceImpl extends ListServiceImpl<MemberAttention> implements MemberAttentionService {

	@Autowired
	private MemberAttentionMapper memberAttentionMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public MemberAttention insert(MemberAttention memberAttention) {
		MemberAttention result = this.save(memberAttention);
		return result;
	}

	@Override
	public PageResult<MemberAttention> findMemberAttentionsByQuery(PageModel page, ProQuery query) {
		PageResult<MemberAttention> pager = new PageResult<MemberAttention>(page);
		int count = memberAttentionMapper.countMemberAttentionsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(memberAttentionMapper.findMemberAttentionsByQuery(page, query));
		return pager;
	}

}

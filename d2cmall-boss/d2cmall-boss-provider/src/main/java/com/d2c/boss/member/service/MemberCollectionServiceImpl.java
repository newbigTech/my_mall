package com.d2c.boss.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.member.dao.MemberCollectionMapper;
import com.d2c.boss.member.model.MemberCollection;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("memberCollectionService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MemberCollectionServiceImpl extends ListServiceImpl<MemberCollection> implements MemberCollectionService {

	@Autowired
	private MemberCollectionMapper memberCollectionMapper;

	@Override
	public PageResult<MemberCollection> findMemberCollectionsByQuery(PageModel page, ProQuery query) {
		PageResult<MemberCollection> pager = new PageResult<MemberCollection>(page);
		int count = memberCollectionMapper.countMemberCollectionsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(memberCollectionMapper.findMemberCollectionsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public MemberCollection insert(MemberCollection memberCollection) {
		MemberCollection result = this.save(memberCollection);
		return result;
	}

}

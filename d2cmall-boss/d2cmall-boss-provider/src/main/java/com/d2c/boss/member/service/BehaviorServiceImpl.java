package com.d2c.boss.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.member.dao.BehaviorMapper;
import com.d2c.boss.member.model.Behavior;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("behaviorService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BehaviorServiceImpl extends ListServiceImpl<Behavior> implements BehaviorService {

	@Autowired
	private BehaviorMapper behaviorMapper;

	@Override
	public PageResult<Behavior> findBehaviorsByQuery(PageModel page, ProQuery query) {
		PageResult<Behavior> pager = new PageResult<Behavior>(page);
		int count = behaviorMapper.countBehaviorsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(behaviorMapper.findBehaviorsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Behavior insert(Behavior behavior) {
		Behavior result = this.save(behavior);
		return result;
	}

}

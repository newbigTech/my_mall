package com.d2c.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.cms.dao.ShareTaskClickMapper;
import com.d2c.cms.model.ShareTaskClick;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("shareTaskClickService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ShareTaskClickServiceImpl extends ListServiceImpl<ShareTaskClick> implements ShareTaskClickService {

	@Autowired
	private ShareTaskClickMapper shareTaskClickMapper;
	@Autowired
	private ShareTaskService shareTaskService;

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int doClick(ShareTaskClick click) {
		click = this.save(click);
		if (click.getId() > 0) {
			int count = shareTaskClickMapper.countBy(click.getTaskId(), click.getIp());
			if (count <= 0) {
				shareTaskService.doClickSharePoint(click.getTaskDefId(), click.getTaskId());
			}
			return shareTaskClickMapper.doCounted(click.getId());
		}
		return 0;
	}

}

package com.d2c.msg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.msg.dao.PartnerLogMapper;
import com.d2c.msg.model.PartnerLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("partnerLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class PartnerLogServiceImpl extends ListServiceImpl<PartnerLog> implements PartnerLogService {

	@Autowired
	private PartnerLogMapper partnerLogMapper;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public PartnerLog insert(PartnerLog log) {
		return this.save(log);
	}

	@Override
	public PageResult<PartnerLog> findByPartnerId(Long partnerId, PageModel page) {
		PageResult<PartnerLog> pager = new PageResult<PartnerLog>(page);
		int totalCount = partnerLogMapper.countByPartnerId(partnerId);
		List<PartnerLog> list = new ArrayList<PartnerLog>();
		if (totalCount > 0) {
			list = partnerLogMapper.findByPartnerId(partnerId, page);
			pager.setTotalCount(totalCount);
		}
		pager.setList(list);
		return pager;
	}

}

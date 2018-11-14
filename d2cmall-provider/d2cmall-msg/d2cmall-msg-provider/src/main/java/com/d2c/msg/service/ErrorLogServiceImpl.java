package com.d2c.msg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.msg.dao.ErrorLogMapper;
import com.d2c.msg.model.ErrorLog;
import com.d2c.msg.query.ErrorLogSearcher;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("errorLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class ErrorLogServiceImpl extends ListServiceImpl<ErrorLog> implements ErrorLogService {

	@Autowired
	private ErrorLogMapper errorLogMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ErrorLog insert(ErrorLog errorLog) {
		return this.save(errorLog);
	}

	@Override
	public PageResult<ErrorLog> findBySearcher(PageModel page, ErrorLogSearcher searcher) {
		PageResult<ErrorLog> pager = new PageResult<ErrorLog>(page);
		int totalCount = errorLogMapper.countBySearcher(searcher);
		if (totalCount > 0) {
			List<ErrorLog> list = errorLogMapper.findBySearcher(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int delete(Long id) {
		return deleteById(id);
	}

}

package com.d2c.msg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.msg.dao.ProductReportLogMapper;
import com.d2c.msg.model.ProductReportLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service(value = "productReportLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductReportLogServiceImpl extends ListServiceImpl<ProductReportLog> implements ProductReportLogService {

	@Autowired
	private ProductReportLogMapper productReportLogMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ProductReportLog insert(ProductReportLog log) {
		return this.save(log);
	}

	public PageResult<ProductReportLog> findByReportId(Long reportId, PageModel page) {
		PageResult<ProductReportLog> pager = new PageResult<>(page);
		int totalCount = productReportLogMapper.countByReportId(reportId);
		if (totalCount > 0) {
			List<ProductReportLog> list = productReportLogMapper.findByReportId(reportId, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

}

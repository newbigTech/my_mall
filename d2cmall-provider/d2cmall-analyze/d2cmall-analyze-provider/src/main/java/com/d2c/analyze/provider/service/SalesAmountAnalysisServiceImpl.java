package com.d2c.analyze.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.analyze.api.model.SalesAmountAnalysis;
import com.d2c.analyze.api.service.SalesAmountAnalysisService;
import com.d2c.analyze.provider.dao.SalesAmountAnalysisMapper;
import com.d2c.mybatis.service.ListServiceImpl;

@Service(protocol = "dubbo")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class SalesAmountAnalysisServiceImpl extends ListServiceImpl<SalesAmountAnalysis>
		implements SalesAmountAnalysisService {

	@Autowired
	private SalesAmountAnalysisMapper salesAmountAnalysisMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public SalesAmountAnalysis insert(SalesAmountAnalysis salesAmountAnalysis) {
		return this.save(salesAmountAnalysis);
	}

	@Override
	public SalesAmountAnalysis findByLastOne() {
		return salesAmountAnalysisMapper.findByLastOne();
	}

}

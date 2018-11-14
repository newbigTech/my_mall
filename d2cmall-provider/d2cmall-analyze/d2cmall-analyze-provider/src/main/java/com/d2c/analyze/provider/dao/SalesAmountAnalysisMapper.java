package com.d2c.analyze.provider.dao;

import com.d2c.analyze.api.model.SalesAmountAnalysis;
import com.d2c.mybatis.mapper.SuperMapper;

public interface SalesAmountAnalysisMapper extends SuperMapper<SalesAmountAnalysis> {

	SalesAmountAnalysis findByLastOne();
}

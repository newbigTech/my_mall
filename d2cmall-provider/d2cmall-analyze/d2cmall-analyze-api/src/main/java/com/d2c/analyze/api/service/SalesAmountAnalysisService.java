package com.d2c.analyze.api.service;

import com.d2c.analyze.api.model.SalesAmountAnalysis;

public interface SalesAmountAnalysisService {

	SalesAmountAnalysis insert(SalesAmountAnalysis salesAmountAnalysis);

	SalesAmountAnalysis findByLastOne();
}

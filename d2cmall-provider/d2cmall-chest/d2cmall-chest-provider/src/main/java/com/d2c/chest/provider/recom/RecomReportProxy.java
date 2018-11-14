package com.d2c.chest.provider.recom;

import com.d2c.chest.provider.mongo.model.RecomReportDO;
import com.d2c.common.mongodb.report.ReportProxy;

public class RecomReportProxy extends ReportProxy<RecomReportDO> {
	
	public RecomReportProxy() {
		super(new RecomReportDO());
	}
	
	@Override
	protected String getName() {
		return "商品推荐值";
	}

	@Override
	public int getSaveLimit(){
		return 100;
	}
	
}

package com.d2c.chest.provider.similar;

import java.util.Date;

import com.d2c.chest.api.entity.SimilarSchemeDO;
import com.d2c.chest.provider.mongo.model.SimilarReportDO;
import com.d2c.chest.provider.mongo.model.SimilarReportDO.SimilarType;
import com.d2c.common.base.utils.DateUt;
import com.d2c.common.mongodb.report.ReportProxy;

public class SimilarReportProxy extends ReportProxy<SimilarReportDO> {
	
	private String schemeName;
	
	private boolean isRestart = false;
	
	public SimilarReportProxy() {
		super(new SimilarReportDO());
	}
	
	public SimilarReportProxy(SimilarReportDO rp) {
		super(rp);
		isRestart = true;
	}
	
	public void initUpdate(Date lastDate){
		report.setType(SimilarType.UPDATE);
		report.setLastDate(lastDate);
		report.setName(report.getName() + "_from_" + DateUt.minute2str(lastDate));
	}
	
	public boolean addOne(Object beanId, int tgCount) {
		report.setBeanId(beanId);
		report.setTgCount(tgCount);
		return addOne();
	}
	
	public boolean updateOne(Object beanId, int count) {
		report.addBeanCount();
		report.addCount(count);
		debug(beanId + " 更新相似度，反向同步 " + count + " 条数据...");
		return true;
	}

	public void start(SimilarSchemeDO scheme){
		this.report.setScheme(scheme);
		this.schemeName = scheme.getSchemeName();
		start();
	}

	@Override
	protected String getName() {
		return schemeName;
	}

	@Override
	public int getSaveLimit() {
		return 10;
	}

	public boolean isRestart() {
		return isRestart;
	}

	public void setRestart(boolean isRestart) {
		this.isRestart = isRestart;
	}
}

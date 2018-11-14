package com.d2c.chest.provider.similar;

import java.util.Date;
import java.util.List;

import com.d2c.chest.api.entity.SimilarSchemeDO;
import com.d2c.chest.api.helper.SimilarHelper;
import com.d2c.chest.provider.mongo.dao.SimilarReportMongoDao;
import com.d2c.chest.provider.mongo.model.SimilarReportDO;
import com.d2c.chest.provider.similar.resolver.SimilarResolver;
import com.d2c.common.base.utils.DateUt;
import com.d2c.common.core.helper.SpringHelper;
import com.d2c.product.search.service.ProductSearcherQueryService;

public class SimilarJob {
	
	private SimilarSchemeDO scheme;
	private SimilarResolver rsv;
	private SimilarReportProxy report;

	private SimilarHandler handler;
	private ProductSearcherQueryService service;
	private SimilarReportMongoDao similarReportMongoDao;
	
	private List<? extends Object> tgList;

	public SimilarJob(SimilarHandler handler, ProductSearcherQueryService serv) {
		this.handler = handler;
		this.service = serv;
		similarReportMongoDao = SpringHelper.getBean(SimilarReportMongoDao.class);
	}

	/**
	 * 根据方案执行相似度对比
	 */
	public SimilarReportDO similar(SimilarSchemeDO scheme) {
		this.scheme = scheme;
		this.rsv = handler.buildResolver(scheme.getId());
		
		//尝试恢复上一次任务
		SimilarReportDO rp = similarReportMongoDao.findLastUnDoneReport(scheme.getId());
		if(rp != null){
			report = new SimilarReportProxy(rp);
		}else{
			report = new SimilarReportProxy();
			report.start(scheme);
		}
		
		//判断方案是否被修改并执行
		if (scheme.getHasExec()) {
			updateSimilar();
		} else {
			similarAll();
			handler.doAfterJob(scheme.getId());
		}
		report.end();
		return report.getReport();
	}

	// *************************************************
	

	/**
	 * 根据方案执行相似度对比
	 */
	private void similarAll() {
		SimilarBucket bucket = new SimilarBucket(service, scheme, null);
		if(report.isRestart()){
			bucket.setStart(report.getReport().getBeanCount() - 1);
			report.restart(bucket.getCount());
		}
		
		while (bucket.hasNext()) {
			Object bean = bucket.next();
			try{
				report.addOne(findId(bean), similarOne(bean));
			}catch (Exception e) {
				report.error(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 单个商品相似度计算 计算商品和所有该类目下的所有商品
	 */
	private int similarOne(Object bean) {
		if(tgList == null){
			tgList = service.findProductTargets(scheme.getCategoryId().longValue(), 1, scheme.getMaxSize());
			report.debug("相似度目标列表查询完成..." + scheme.getMaxSize());
		}
		
		return handler.similarOneImpl(rsv, scheme, bean, tgList, true);
	}
	
	// *********************** update 更新商品相似度  *************************

	/**
	 * 方案未修改, 根据修改的商品重新计算相似度
	 */
	private void updateSimilar() {
		SimilarReportDO last = handler.findLastReport(scheme.getId());
		Date lastDate;
		if(last != null){
			lastDate = last.getGmtCreate();
		}else{
			lastDate = DateUt.dayAdd(-7);
		}

		SimilarBucket bucket = new SimilarBucket(service, scheme, null);
		bucket.setLastDate(lastDate);
		
		report.initUpdate(lastDate);
		while(bucket.hasNext()){
			Object bean = bucket.next();
			try{
				report.updateOne(findId(bean), updateSimilarOne(bean));
			}catch (Exception e) {
				report.error(e.getMessage(), e);
			}
		}
	}

	private int updateSimilarOne(Object bean) {
		similarOne(bean);
		return handler.updateSimilarOneImpl(rsv, scheme, bean);
	}
	
	// *********************** private 更新商品相似度  *************************
	
	private Object findId(Object bean){
		return SimilarHelper.findId(bean);
	}

}

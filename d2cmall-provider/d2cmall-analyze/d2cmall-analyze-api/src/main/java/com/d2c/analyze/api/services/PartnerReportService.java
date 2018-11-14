package com.d2c.analyze.api.services;

import java.util.Date;

import com.d2c.analyze.api.mongo.model.PartnerReportDO;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.api.query.model.MongoQuery;

/**
 * 买手销售数据
 * @author wull
 */
public interface PartnerReportService {

	public PageResult<PartnerReportDO> findPageQuery(MongoQuery query, PageModel pager);

	public Object buildReportOnDay(Date date);

}
 
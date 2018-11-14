package com.d2c.quartz.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.d2c.analyze.api.services.PartnerSaleTimeService;
import com.d2c.common.base.utils.DateUt;
import com.d2c.quartz.task.base.BaseTask;

/**
 * 定时买手统计数据
 * <p>
 * 每天7点
 * 
 * @author wull
 */
@Component
public class ReportPartnerSaleTask extends BaseTask {

	@Reference
	private PartnerSaleTimeService partnerSaleTimeService;

	@Scheduled(cron = "0 10 0 * * ?")
	public void execute() {
		super.exec();
	}

	@Override
	public void execImpl() {
		Date day = DateUt.dayBack(1);
		partnerSaleTimeService.buildReportOnDay(day);
	}

}

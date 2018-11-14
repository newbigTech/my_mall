package com.d2c.quartz.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.d2c.analyze.api.services.PartnerSaleService;
import com.d2c.quartz.task.base.BaseTask;

/**
 * 定时买手统计数据
 * <p>
 * 每2个小时
 * 
 * @author wull
 */
@Component
public class ProcessPartnerSaleTask extends BaseTask {

	@Reference
	private PartnerSaleService partnerSaleService;

	@Scheduled(cron = "0 0 0/2 * * ?")
	public void execute() {
		super.exec();
	}

	@Override
	public void execImpl() {
		partnerSaleService.buildReport();
	}

}

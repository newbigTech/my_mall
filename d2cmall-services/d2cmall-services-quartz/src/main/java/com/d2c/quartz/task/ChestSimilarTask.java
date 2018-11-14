package com.d2c.quartz.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.d2c.chest.api.service.SimilarService;
import com.d2c.quartz.task.base.BaseTask;

/**
 * 商品相似度计算任务
 * 
 * @author wull
 */
@Component
public class ChestSimilarTask extends BaseTask {

	@Reference(timeout = 1200000)
	private SimilarService similarService;

	@Scheduled(cron = "0 30 0/6 * * ?")
	public void execute() {
		super.exec();
	}

	@Override
	public void execImpl() {
		similarService.buildSimilarJob();
	}

}

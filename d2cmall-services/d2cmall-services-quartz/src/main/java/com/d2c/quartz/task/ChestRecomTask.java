package com.d2c.quartz.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.d2c.chest.api.service.RecomService;
import com.d2c.quartz.task.base.BaseTask;

/**
 * 商品推荐计算任务
 * 
 * @author wull
 */
@Component
public class ChestRecomTask extends BaseTask {

	@Reference(timeout = 1200000)
	private RecomService recomService;

	@Scheduled(cron = "0 0 5 * * ?")
	public void execute() {
		super.exec();
	}

	@Override
	public void execImpl() {
		recomService.buildAllRecomInJob();
	}

}

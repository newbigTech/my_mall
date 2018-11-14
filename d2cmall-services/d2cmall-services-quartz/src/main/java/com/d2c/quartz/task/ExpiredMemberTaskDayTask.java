package com.d2c.quartz.task;

import org.springframework.stereotype.Component;

import com.d2c.member.mongo.services.MemberTaskExecService;
import com.d2c.quartz.task.base.BaseTask;

/**
 * 定时刷新用户每日任务
 * 
 * @author wull
 */
@Component
public class ExpiredMemberTaskDayTask extends BaseTask {

	private MemberTaskExecService memberTaskExecService;

	// @Scheduled(cron = "30 3 0 * * ?")
	public void execute() {
		super.exec();
	}

	@Override
	public void execImpl() {
		memberTaskExecService.refreshOnDayTask();
	}

}

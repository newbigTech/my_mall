package com.d2c.quartz.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.RpcContext;
import com.d2c.msg.service.SmsService;
import com.d2c.quartz.task.base.BaseTask;
import com.d2c.quartz.task.properties.QuartzProperties;

@Component
public class NoticeSmsRestTask extends BaseTask {

	@Autowired
	private SmsService smsService;
	@Autowired
	private QuartzProperties props;

	/**
	 * 剩余短信条数
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Scheduled(cron = "0 0 8 * * ?")
	public void execute() {
		if (properties.getDebug()) {
			return;
		}
		super.exec();
	}

	@Override
	public void execImpl() {
		if (props.getMsg() == 1) {
			try {
				smsService.getBalance();
				Future<String> fooFuture = RpcContext.getContext().getFuture();
				smsService.sendSMS(new String[] { "13336024908" }, "短信剩余条数:" + fooFuture.get());
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
		}
	}

}

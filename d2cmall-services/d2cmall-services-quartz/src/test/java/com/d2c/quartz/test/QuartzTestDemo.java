package com.d2c.quartz.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.quartz.task.ProcessProductStockTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext-test.xml" })
@Transactional("transactionManager")
public class QuartzTestDemo extends AbstractJUnit4SpringContextTests {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProcessProductStockTask task;

	@Test
	public void test() {
		task.execute();
	}

}
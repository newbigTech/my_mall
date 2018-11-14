package com.d2c.ethereum.test;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d2c.ethereum.utils.EtheUt;

public class EtheTest {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void test() {
		try {
			logger.info("开始执行测试....");
			byte[] a = EtheUt.toHex("112233");
			byte[] b = "112233".getBytes();

			logger.info("a...." + new String(a));
			logger.info("b...." + new String(b));
			
			Assert.assertArrayEquals("错误", a, b);
			
		} catch (Exception e) {
			logger.error("异常", e);
		}
	}

}

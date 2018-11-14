package com.d2c.order.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.d2c.common.core.test.BaseTest;
import com.d2c.order.service.CollageOrderService;

public class TestDemo extends BaseTest {

	@Autowired
	private CollageOrderService collageOrderService;

	@Test
	public void test() {
		collageOrderService.doPaySuccess("Q15334743316706121", 313228L, "4200000151201808055581551917", 6,
				new BigDecimal(520));
	}

}

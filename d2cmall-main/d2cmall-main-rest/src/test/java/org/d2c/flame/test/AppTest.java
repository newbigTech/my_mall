package org.d2c.flame.test;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.d2c.common.base.utils.BeanUt;
import com.d2c.common.base.utils.JsonUt;
import com.d2c.common.core.test.BaseTest;
import com.d2c.product.search.model.SearcherProduct;

/**
 * 测试
 */
public class AppTest extends BaseTest {
	
	@Test
	public void test(){
		logger.info("开始测试方法..");
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("productId", 123);
		map.put("minPrice", "123.211");
		
		SearcherProduct bean = new SearcherProduct();
		BeanUt.apply(bean, map);
		
		logger.info("bean.." + JsonUt.serialize(bean));
		
	}
	
}

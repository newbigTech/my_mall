package org.d2c.flame.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.d2c.common.api.convert.ConvertHelper;
import com.d2c.common.base.utils.JsonUt;
import com.d2c.common.core.test.SimpleTest;

public class ViewTest extends SimpleTest {
	
	@Test
	public void test() {
		
		TestDTO dto = new TestDTO();
		dto.setName("wull");
		dto.setAge(18);
		
		List<TestDTO> list = new ArrayList<>();
		list.add(dto);
		
		TestVO vo = TestVO.convert(dto, TestVO.class);
		logger.info("TestVO.." + vo);
		
		TestDTO bean = vo.convertBack(TestDTO.class);
		logger.info("TestDTO.." + bean);
		
		List<TestVO> vl = ConvertHelper.convertList(list, TestVO.class);
		logger.info("List<TestVO>.." + JsonUt.serialize(vl));
		
	}
}

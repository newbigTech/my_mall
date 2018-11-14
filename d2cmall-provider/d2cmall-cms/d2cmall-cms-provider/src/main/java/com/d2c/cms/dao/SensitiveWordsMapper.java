package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.SensitiveWords;
import com.d2c.cms.query.SensitiveWordsSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface SensitiveWordsMapper extends SuperMapper<SensitiveWords> {

	int countBySearcher(@Param("searcher") SensitiveWordsSearcher searcher);

	List<SensitiveWords> findBysearcher(@Param("searcher") SensitiveWordsSearcher searcher,
			@Param("pager") PageModel pager);

	int deleteById(@Param("id") Long id);

	int updateStatus(@Param("id") Long id, @Param("status") Integer status,
			@Param("lastModifyMan") String lastModifyMan);

	int findBySensitiveWords(@Param("sensitiveWords") String sensitiveWords);

	SensitiveWords findByKeyword(@Param("keyword") String keyword);

}

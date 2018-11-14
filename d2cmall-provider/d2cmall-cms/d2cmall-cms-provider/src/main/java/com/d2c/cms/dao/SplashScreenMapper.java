package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.SplashScreen;
import com.d2c.cms.query.SplashScreenSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface SplashScreenMapper extends SuperMapper<SplashScreen> {
	Integer countBySearcher(@Param("searcher") SplashScreenSearcher searcher);

	List<SplashScreen> findBySearcher(@Param("pager") PageModel page, @Param("searcher") SplashScreenSearcher searcher);

	SplashScreen findCurrentVersion();

	int updateStatus(@Param("id") Long id, @Param("status") Integer status,
			@Param("lastModifyMan") String lastModifyMan);

	int doTiming(@Param("id") Long id, @Param("timing") Integer timing);

	int doDownAll();
}

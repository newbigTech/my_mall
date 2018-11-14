package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.AppVersion;
import com.d2c.cms.query.AppVersionSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface AppVersionMapper extends SuperMapper<AppVersion> {

	int countBySearch(@Param("searcher") AppVersionSearcher searcher);

	List<AppVersion> findBySearch(@Param("searcher") AppVersionSearcher searcher, @Param("page") PageModel page);

	List<AppVersion> findAllVersion(@Param("appTerminal") String appTerminal);

	AppVersion findByVersion(@Param("version") String version, @Param("appTerminal") String appTerminal,
			@Param("type") String type);

	Long findUpgrade(@Param("appTerminal") String appTerminal, @Param("type") String type, @Param("digit") Long digit);

	AppVersion findLastVersion(@Param("appTerminal") String appTerminal, @Param("type") String typen,
			@Param("digit") Long digit);

	AppVersion findSameVersion(@Param("version") AppVersion appVersion);

	int deleteById(@Param("id") Long id, @Param("lastModifyMan") String lastModifyMan);

	int updateForce(@Param("id") Long id, @Param("upgrade") Integer upgrade, @Param("name") String name);

}

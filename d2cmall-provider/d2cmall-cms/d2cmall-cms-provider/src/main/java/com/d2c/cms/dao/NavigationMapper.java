package com.d2c.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.Navigation;
import com.d2c.mybatis.mapper.SuperMapper;

public interface NavigationMapper extends SuperMapper<Navigation> {

	List<Navigation> findAll(@Param("version") String version);

	int updatePath(Map<String, Object> map);

	int updateSequence(@Param("id") Long id, @Param("sequence") int sequence);

	List<Navigation> findByNameAndParentId(Map<String, Object> map);

	Navigation findByCode(@Param("code") String code);

	List<Navigation> findRoots(@Param("status") Integer status, @Param("version") String version);

	List<Navigation> findByParentId(@Param("parentId") Long parentId, @Param("status") Integer status,
			@Param("version") String version);

	List<Navigation> findChildrens(@Param("parentId") Long parentId);

	List<Navigation> findActiveChildrens(@Param("parentId") Long parentId);

	int delete();
}

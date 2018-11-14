package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.SubModule;
import com.d2c.cms.query.PageContentSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface SubModuleMapper extends SuperMapper<SubModule> {

	List<SubModule> findByParent(@Param("parent") String parent);

	List<SubModule> findByParentAndCategory(@Param("categoryId") Long categoryId);

	List<SubModule> findBySearcher(@Param("searcher") PageContentSearcher searcher, @Param("pager") PageModel pager);

	int countBySearcher(@Param("searcher") PageContentSearcher searcher);

	int updateDefault(@Param("id") Long id, @Param("is_default") int is_default);

	int delete(Long id);

	int updateCancelDefault(String parent);

	int updateStatus(@Param("id") Long id, @Param("status") Integer status,
			@Param("lastModifyMan") String lastModifyMan);

	int updateVersion(@Param("id") Long id, @Param("newVersion") Integer newVersion);

	List<SubModule> findCategory(@Param("parent") String parent);

	int updateCategoryId(@Param("id") Long id, @Param("categoryId") Long categoryId);
}

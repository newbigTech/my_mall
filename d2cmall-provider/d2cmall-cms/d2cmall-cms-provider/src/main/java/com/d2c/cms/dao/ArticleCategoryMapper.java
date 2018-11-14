package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.ArticleCategory;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ArticleCategoryMapper extends SuperMapper<ArticleCategory> {

	List<ArticleCategory> findAll();

	int delete(Long id);

	int updateSort(@Param("id") Long id, @Param("orderList") Integer sort);
}

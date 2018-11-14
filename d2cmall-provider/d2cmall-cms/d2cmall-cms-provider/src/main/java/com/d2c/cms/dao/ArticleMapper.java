package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.dto.ArticleDto;
import com.d2c.cms.model.Article;
import com.d2c.cms.query.ArticleSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ArticleMapper extends SuperMapper<Article> {

	Article findById(Long id);

	Article findOneByName(String name);

	List<Article> findByCategoryId(@Param("catalogId") Long catalogId, @Param("pager") PageModel pager);

	List<Article> findBySearcher(@Param("search") ArticleSearcher search, @Param("pager") PageModel pager);

	int countBySearcher(@Param("search") ArticleSearcher search);

	int doPublish(Article article);

	int doTop(Long id);

	int doCancelTop(Long id);

	int doRecommend(Long id);

	int doCancelRecommend(Long id);

	int delete(Long id);

	int countByCategoryId(@Param("catalogId") Long catalogId);

	List<ArticleDto> findDtoBySearcher(@Param("search") ArticleSearcher search, @Param("pager") PageModel pager);

	ArticleDto findDtoById(Long id);
}

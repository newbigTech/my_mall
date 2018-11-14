package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.QuizBank;
import com.d2c.cms.query.QuizBankSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface QuizBankMapper extends SuperMapper<QuizBank> {

	int deleteById(@Param("id") Long id);

	int countBySearcher(@Param("searcher") QuizBankSearcher searcher);

	List<QuizBank> findBySearcher(@Param("searcher") QuizBankSearcher searcher, @Param("page") PageModel page);

	int updateStatus(@Param("id") Long id, @Param("mark") Integer mark, @Param("operator") String operator);

	List<QuizBank> findByType(@Param("type") Integer type);

	int updateQuizBank(@Param("quiz") QuizBank quiz);

}

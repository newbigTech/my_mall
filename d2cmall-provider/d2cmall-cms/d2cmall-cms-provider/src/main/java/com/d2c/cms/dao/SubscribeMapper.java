package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.Subscribe;
import com.d2c.cms.model.Subscribe.SubType;
import com.d2c.cms.query.SubscribeSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface SubscribeMapper extends SuperMapper<Subscribe> {

	List<Subscribe> findByType(@Param("subType") SubType subType);

	int countSubBySearcher(@Param("searcher") SubscribeSearcher searcher);

	List<Subscribe> findSubBySearcher(@Param("searcher") SubscribeSearcher searcher, @Param("pager") PageModel pager);

	Subscribe findBySubscribe(@Param("subscribe") String subscribe);

}
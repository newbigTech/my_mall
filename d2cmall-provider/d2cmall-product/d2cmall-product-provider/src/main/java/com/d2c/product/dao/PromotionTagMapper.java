package com.d2c.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.product.model.PromotionTag;
import com.d2c.product.query.PromotionTagSearcher;

public interface PromotionTagMapper extends SuperMapper<PromotionTag> {

	List<PromotionTag> findBySearch(@Param("searcher") PromotionTagSearcher searcher, @Param("pager") PageModel page);

	int countBySearch(@Param("searcher") PromotionTagSearcher searcher);

	int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}

package com.d2c.product.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.product.model.CrawDesigner;
import com.d2c.product.query.CrawDesignerSearcher;

public interface CrawDesignerMapper extends SuperMapper<CrawDesigner> {

	int updateCrawDateById(@Param("crawDate")Date crawDate,@Param("id") Long id);

	int countBySearcher(@Param("searcher") CrawDesignerSearcher searcher);

	List<CrawDesigner> findBySearcher(@Param("searcher") CrawDesignerSearcher searcher,@Param("pager") PageModel page);

}

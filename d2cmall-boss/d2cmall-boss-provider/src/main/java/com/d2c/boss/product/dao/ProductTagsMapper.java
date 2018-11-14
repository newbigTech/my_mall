package com.d2c.boss.product.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.boss.product.model.ProductTags;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ProductTagsMapper extends SuperMapper<ProductTags> {

	int countProductTagsByQuery(@Param("query") ProQuery query);

	List<ProductTags> findProductTagsByQuery(@Param("pager") PageModel pager, @Param("query") ProQuery query);

	Date getLastSysDate();
}
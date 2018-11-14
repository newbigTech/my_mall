package com.d2c.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.PartnerGift;
import com.d2c.order.query.PartnerGiftSearcher;

public interface PartnerGiftMapper extends SuperMapper<PartnerGift> {

	List<PartnerGift> findBySearcher(@Param("searcher") PartnerGiftSearcher searcher, @Param("pager") PageModel page);

	Integer countBySearcher(@Param("searcher") PartnerGiftSearcher searcher);

}

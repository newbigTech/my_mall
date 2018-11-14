package com.d2c.order.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.AuctionOffer;
import com.d2c.order.query.AuctionOfferSearcher;

public interface AuctionOfferMapper extends SuperMapper<AuctionOffer> {

	List<AuctionOffer> findBySearch(@Param("searcher") AuctionOfferSearcher searcher, @Param("pager") PageModel pager);

	int countBySearch(@Param("searcher") AuctionOfferSearcher searcher);

	AuctionOffer findTopByAuctionId(@Param("auctionId") Long auctionId);

	int doSuccess(@Param("id") Long id);

	int doOut(@Param("auctionId") Long auctionId, @Param("offer") BigDecimal offer);

	int doMerge(@Param("sourceId") Long memberSourceId, @Param("targetId") Long memberTargetId);

}
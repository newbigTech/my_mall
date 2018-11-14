package com.d2c.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.O2oSubscribeItem;

public interface O2oSubscribeItemMapper extends SuperMapper<O2oSubscribeItem> {

	List<O2oSubscribeItem> findBySubscribeId(Long subscribeId);

	int countBySubscribeId(Long subscribeId);

	int deleteByIdAndMemberId(@Param("id") Long id, @Param("memberInfoId") Long memberInfoId);

	int deleteBySubscribeIdAndMemberId(@Param("subscribeId") Long subscribeId,
			@Param("memberInfoId") Long memberInfoId);

	int deleteByProductIds(@Param("ids") List<Long> ids);

	int doMerge(@Param("sourceId") Long memberSourceId, @Param("targetId") Long memberTargetId);
}

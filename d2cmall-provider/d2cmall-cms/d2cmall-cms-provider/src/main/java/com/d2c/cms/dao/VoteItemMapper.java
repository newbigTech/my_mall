package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.dto.VoteSelectionDto;
import com.d2c.cms.model.VoteItem;
import com.d2c.cms.query.VoteItemSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface VoteItemMapper extends SuperMapper<VoteItem> {
	
	List<VoteSelectionDto> findByDefIdGroupBySelection(@Param("searcher") VoteItemSearcher searcher,
			@Param("page") PageModel page);

	Integer countByDefIdGroupBySelection(@Param("searcher") VoteItemSearcher searcher);

	int updateByDef(@Param("selectionId") Long selectionId, @Param("defTitle") String defTitle,
			@Param("selectName") String selectName, @Param("pic") String pic, @Param("coef") Double coef);

	int updateStatusByDef(@Param("selectionId") Long selectionId, @Param("status") Integer status);

	List<VoteItem> findByDefIdAndMemberId(@Param("defId") Long defId, @Param("memberId") Long memberId);

	int countBySearcher(@Param("searcher") VoteItemSearcher searcher);
}

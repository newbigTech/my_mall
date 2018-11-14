package com.d2c.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.VoteSelection;
import com.d2c.cms.query.VoteSelectionSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface VoteSelectionMapper extends SuperMapper<VoteSelection> {

	List<VoteSelection> findByDefId(Long defId);

	int update(VoteSelection voteSelection);

	int delete(Long id);

	List<VoteSelection> findByIds(@Param("ids") Long[] ids);

	List<VoteSelection> findBySearcher(@Param("searcher") VoteSelectionSearcher searcher,
			@Param("page") PageModel page);

	int countBySearcher(@Param("searcher") VoteSelectionSearcher searcher);

	int updateByDefId(@Param("defId") Long defId, @Param("title") String title);
}

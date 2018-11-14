package com.d2c.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.d2c.cms.model.FeedBack;
import com.d2c.cms.query.FeedBackSearcher;
import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;

public interface FeedBackMapper extends SuperMapper<FeedBack> {

	List<FeedBack> findBySearcher(@Param("searcher") FeedBackSearcher searcher, @Param("pager") PageModel pager);

	Integer countBySearcher(@Param("searcher") FeedBackSearcher searcher);

	List<Map<String, Object>> findCountGroupByType(@Param("searcher") FeedBackSearcher searcher);

	int deleteByIds(@Param("ids") Long[] ids);

	int doMerge(@Param("sourceId") Long memberSourceId, @Param("targetId") Long memberTargetId);

	int doReply(@Param("id") Long id, @Param("reply") String reply, @Param("replyMan") String replyMan);

	int doClose(Long id);

	List<String> findVersions();

	int updateStatus(@Param("id") Long id, @Param("status") Integer status, @Param("meno") String meno);
}

package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.MessageDef;
import com.d2c.msg.query.MessageDefSearcher;
import com.d2c.mybatis.mapper.SuperMapper;

public interface MessageDefMapper extends SuperMapper<MessageDef> {

	List<MessageDef> findBySearch(@Param("pager") PageModel page, @Param("searcher") MessageDefSearcher searcher);

	int countBySearch(@Param("searcher") MessageDefSearcher searcher);

	int updateStatusById(@Param("id") Long id, @Param("status") int status);

	int updateDelayTime(@Param("id") Long id, @Param("timestamp") Long timestamp);

}
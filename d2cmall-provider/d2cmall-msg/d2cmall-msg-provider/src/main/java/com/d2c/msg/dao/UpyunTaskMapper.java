package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.msg.model.UpyunTask;
import com.d2c.mybatis.mapper.SuperMapper;

public interface UpyunTaskMapper extends SuperMapper<UpyunTask> {
	UpyunTask findByTaskIds(String taskIds);

	List<String> findPicsByTaskIds(@Param("taskIds") String[] taskIds);
}

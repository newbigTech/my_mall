package com.d2c.msg.service;

import java.util.List;

import com.d2c.msg.model.UpyunTask;

public interface UpyunTaskService {

	UpyunTask findByTaskIds(String taskIds);

	UpyunTask insert(UpyunTask upyunTask);

	UpyunTask callBackInsert(UpyunTask upyunTask);

	List<String> findPicsByTaskIds(String[] taskIds);
}

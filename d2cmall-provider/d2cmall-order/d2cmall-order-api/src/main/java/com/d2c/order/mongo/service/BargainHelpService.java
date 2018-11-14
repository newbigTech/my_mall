package com.d2c.order.mongo.service;

import java.util.Date;

public interface BargainHelpService {

	/**
	 * 统计unionId在规定时间内绑砍的次数
	 * 
	 * @param unionId
	 * @param begainDate
	 * @param endDate
	 * @return
	 */
	int countByUnionId(String unionId, Date begainDate, Date endDate);
	
}

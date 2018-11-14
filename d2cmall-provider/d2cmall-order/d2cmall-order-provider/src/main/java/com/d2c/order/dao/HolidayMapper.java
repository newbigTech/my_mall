package com.d2c.order.dao;

import java.util.Date;
import java.util.List;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.Holiday;

public interface HolidayMapper extends SuperMapper<Holiday> {

	int replaceInto(Date holiday);

	List<Date> findList(Date date);

}

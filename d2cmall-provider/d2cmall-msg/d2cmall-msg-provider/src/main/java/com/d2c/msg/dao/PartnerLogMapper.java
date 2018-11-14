package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.PartnerLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface PartnerLogMapper extends SuperMapper<PartnerLog> {

	List<PartnerLog> findByPartnerId(@Param("partnerId") Long partnerId, @Param("page") PageModel page);

	int countByPartnerId(Long partnerId);

}

package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.ProductReportLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface ProductReportLogMapper extends SuperMapper<ProductReportLog> {

	List<ProductReportLog> findByReportId(@Param("reportId") Long reportId, @Param("page") PageModel page);

	int countByReportId(@Param("reportId") Long reportId);

}

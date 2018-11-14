package com.d2c.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.model.RequisitionLog;
import com.d2c.mybatis.mapper.SuperMapper;

public interface RequisitionLogMapper extends SuperMapper<RequisitionLog> {

	int countByRequisitionId(@Param("requisitionId") Long requisitionId);

	int countByRequisitionItemId(@Param("requisitionItemId") Long requisitionItemId,
			@Param("requisitionId") Long requisitionId);

	List<RequisitionLog> findByRequisitionItemId(@Param("requisitionItemId") Long requisitionItemId,
			@Param("requisitionId") Long requisitionId, @Param("pager") PageModel page);

	List<RequisitionLog> findByRequisitionId(@Param("requisitionId") Long requisitionId,
			@Param("pager") PageModel page);

	int updateSnByItemIds(@Param("ids") List<Long> ids, @Param("requisitionSn") String requisitionSn);

}

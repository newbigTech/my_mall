package com.d2c.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.ComplaintTrack;

public interface ComplaintTrackMapper extends SuperMapper<ComplaintTrack> {

	List<ComplaintTrack> findTrackByComplaintId(@Param("complaintId") String complaintId);

}

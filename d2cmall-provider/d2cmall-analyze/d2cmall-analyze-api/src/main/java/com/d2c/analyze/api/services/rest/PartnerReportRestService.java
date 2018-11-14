package com.d2c.analyze.api.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.d2c.frame.api.constant.MediaConst;

/**
 * 执行分销商任务
 * @author wull
 */
@Path("partner/report")
public interface PartnerReportRestService {
	
	@GET
    @Path("build/{back}")
    @Produces(MediaConst.JSON)
    @Consumes(MediaConst.JSON)
	public Object build(@PathParam("back") Integer back);

	@GET
    @Path("test")
    @Produces(MediaConst.JSON)
    @Consumes(MediaConst.JSON)
    public Object test();

}
 
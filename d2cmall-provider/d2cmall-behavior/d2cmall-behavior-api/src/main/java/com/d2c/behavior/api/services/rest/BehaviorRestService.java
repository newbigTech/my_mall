package com.d2c.behavior.api.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.d2c.frame.api.constant.MediaConst;

@Path("behavior")
public interface BehaviorRestService {

	@POST
    @Path("test")
    @Produces(MediaConst.JSON)
    @Consumes(MediaConst.JSON)
    public Object test();
	
	@GET
    @Path("update/event")
    @Produces(MediaConst.JSON)
    @Consumes(MediaConst.JSON)
    public Object updateEvent();
	
}

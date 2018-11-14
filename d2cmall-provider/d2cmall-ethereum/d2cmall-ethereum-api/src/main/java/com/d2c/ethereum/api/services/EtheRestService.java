package com.d2c.ethereum.api.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.d2c.frame.api.constant.MediaConst;

@Path("ethe")
public interface EtheRestService {

	@GET
	@Path("test")
	@Produces(value = MediaConst.JSON)
	public Object test();
	
	@GET
	@Path("info")
    public String info();

	@GET
	@Path("block/count")
	public Object countBlock();
	
	@GET
	@Path("miner/start")
    public Long startMining();

	@GET
	@Path("account/add")
    public Object createAccount();
	
	@GET
	@Path("account/{addr}")
    public String getAccount(@PathParam("addr") String addr);

    /**
     * 添加账户金额
     */
	@GET
	@Path("account/balance/add/{addr}")
    public String addBalance(@PathParam("addr") String addr, @QueryParam("value") String value);

}

package com.d2c.frame.provider.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.d2c.common.api.dto.ResDTO;
import com.d2c.frame.api.constant.MediaConst;

public interface RestService<T> {

    /**
     * 根据ID查询数据
     */
	@GET
    @Path("{id}")
    @Produces(MediaConst.JSON)
	public T selectById(@PathParam("id") Integer id);


    /**
     * 新增实体
     */
    @POST
    @Path("")
    @Produces(MediaConst.JSON)
    @Consumes(MediaConst.JSON)
	public ResDTO saveEntity(T entity);


    /**
     * 修改实体
     */
    @POST
    @Path("{id}")
    @Produces(MediaConst.JSON)
    @Consumes(MediaConst.JSON)
	public ResDTO updateEntity(@PathParam("id") Integer id, T entity);
    


    /**
     * 根据ID删除数据
     */
    @GET
    @Path("delete/{id}")
    @Produces(MediaConst.JSON)
    public ResDTO deleteById(@PathParam("id") Integer id);

}

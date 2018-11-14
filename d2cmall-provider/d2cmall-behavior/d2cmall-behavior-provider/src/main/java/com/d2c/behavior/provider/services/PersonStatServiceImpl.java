package com.d2c.behavior.provider.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.behavior.api.mongo.dto.PersonSessionDTO;
import com.d2c.behavior.api.services.PersonStatService;
import com.d2c.behavior.provider.mongo.dao.PersonSessionMongoDao;

/**
 * 用户数据统计
 * @author wull
 */
@Service(protocol = "dubbo")
public class PersonStatServiceImpl implements PersonStatService {

	@Autowired
	private PersonSessionMongoDao personSessionMongoDao;
	
	/**
	 * 会员用户登录统计
	 */
	public List<PersonSessionDTO> findPersonSessionList() {
		return personSessionMongoDao.findPersonSessionList(null, null);
	}

	/**
	 * 访客用户登录统计
	 */
	public List<PersonSessionDTO> findVistorSessionList() {
		return personSessionMongoDao.findVisitorSessionList(null, null);
	}

}

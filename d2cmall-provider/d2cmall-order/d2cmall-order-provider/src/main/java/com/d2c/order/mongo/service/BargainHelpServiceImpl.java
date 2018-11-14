package com.d2c.order.mongo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d2c.order.mongo.dao.BargainHelpMongoDao;

@Service("bargainHelpService")
public class BargainHelpServiceImpl implements BargainHelpService {

	@Autowired
	private BargainHelpMongoDao bargainHelpMongoDao;
	
	@Override
	public int countByUnionId(String unionId, Date begainDate, Date endDate) {
		return bargainHelpMongoDao.countByUnionId(unionId, begainDate, endDate);
	}

}

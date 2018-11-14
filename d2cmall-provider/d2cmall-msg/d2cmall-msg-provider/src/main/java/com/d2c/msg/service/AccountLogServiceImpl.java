package com.d2c.msg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.common.api.page.PageModel;
import com.d2c.msg.dao.AccountLogMapper;
import com.d2c.msg.model.AccountLog;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("accountLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class AccountLogServiceImpl extends ListServiceImpl<AccountLog> implements AccountLogService {

	@Autowired
	private AccountLogMapper accountLogMapper;

	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public AccountLog insert(AccountLog accountLog) {
		return save(accountLog);
	}

	@Override
	public List<AccountLog> findByAccountId(Long accountId, PageModel page) {
		return accountLogMapper.findByAccountId(accountId, page);
	}

}

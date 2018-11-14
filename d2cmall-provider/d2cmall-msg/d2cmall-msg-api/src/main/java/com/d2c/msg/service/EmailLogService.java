package com.d2c.msg.service;

import java.util.Map;

import com.d2c.msg.model.EmailLog;
import com.d2c.msg.model.EmailLog.EmailLogType;

public interface EmailLogService {

	/**
	 * 根据id获取邮件发送日志
	 * 
	 * @param id
	 * @return
	 */
	EmailLog findById(Long id);

	/**
	 * 通过sourceId和type寻找邮件
	 * 
	 * @param sourceId
	 * @param type
	 * @return
	 */
	EmailLog findBySourceIdAndType(Long sourceId, EmailLogType type);

	/**
	 * 根据id使对应的邮件发送次数+1
	 * 
	 * @param id
	 * @return
	 */
	int updateSend(Long id);

	/**
	 * 根据模板发送邮件，如果模板不存在则不发送邮件
	 * 
	 * @param email
	 * @param subject
	 * @param templateId
	 * @param repalce
	 * @param content
	 * @param sourceId
	 * @param type
	 */
	void sendEmail(String email, String subject, Long templateId, Map<String, String> repalce, String content,
			Long sourceId, EmailLogType type);

}

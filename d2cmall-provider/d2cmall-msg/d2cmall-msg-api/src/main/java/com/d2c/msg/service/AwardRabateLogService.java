package com.d2c.msg.service;

import com.d2c.msg.model.AwardRabateLog;

public interface AwardRabateLogService {

	AwardRabateLog insert(AwardRabateLog awardRabateLog) throws Exception;

	AwardRabateLog findByUniqueMark(String uniqueMark);

}

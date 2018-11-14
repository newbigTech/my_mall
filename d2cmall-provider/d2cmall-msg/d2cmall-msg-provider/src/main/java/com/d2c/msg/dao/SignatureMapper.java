package com.d2c.msg.dao;

import com.d2c.msg.model.Signature;
import com.d2c.mybatis.mapper.SuperMapper;

/**
 * 签名信息（微信）
 * 
 * @author xh
 *
 */
public interface SignatureMapper extends SuperMapper<Signature> {

	Signature findByAppid(String appid);

}

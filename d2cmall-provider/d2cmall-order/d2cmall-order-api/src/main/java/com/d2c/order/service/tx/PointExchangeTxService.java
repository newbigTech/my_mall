package com.d2c.order.service.tx;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.d2c.member.model.MemberInfo;
import com.d2c.product.model.PointProduct;

public interface PointExchangeTxService {
	Map<String, JSONObject> doExchange(PointProduct pointProduct, MemberInfo memberInfo);
}

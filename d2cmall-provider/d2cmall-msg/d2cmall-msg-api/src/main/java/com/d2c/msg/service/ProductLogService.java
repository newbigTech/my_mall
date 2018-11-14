package com.d2c.msg.service;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.msg.model.ProductLog;

public interface ProductLogService {

	ProductLog insert(ProductLog log);

	PageResult<ProductLog> findByProductId(Long productId, PageModel page);

}

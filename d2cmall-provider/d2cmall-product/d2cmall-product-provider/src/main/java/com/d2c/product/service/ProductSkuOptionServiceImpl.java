package com.d2c.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.mybatis.service.ListServiceImpl;
import com.d2c.product.dao.ProductSkuOptionMapper;
import com.d2c.product.model.ProductSkuOption;

@Service("productSkuOptionService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class ProductSkuOptionServiceImpl extends ListServiceImpl<ProductSkuOption> implements ProductSkuOptionService {

	@Autowired
	private ProductSkuOptionMapper productSkuOptionMapper;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public ProductSkuOption insert(ProductSkuOption productSkuOption) {
		return this.save(productSkuOption);
	}

	@Override
	public List<ProductSkuOption> findByProductId(Long productId) {
		return productSkuOptionMapper.findByProductId(productId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int updateMarkByProductId(Long productId, Integer mark) {
		return productSkuOptionMapper.updateMarkByProductId(productId, mark);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int update(ProductSkuOption productSkuOption) {
		return this.updateNotNull(productSkuOption);
	}

}

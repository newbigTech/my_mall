package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.product.dao.ProductSkuMapper;
import com.d2c.boss.product.model.ProductSku;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("productSkuService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductSkuServiceImpl extends ListServiceImpl<ProductSku> implements ProductSkuService {
	@Autowired
	private ProductSkuMapper productSkuMapper;

	@Override
	public PageResult<ProductSku> findProductSkusByQuery(PageModel page, ProQuery query) {

		PageResult<ProductSku> pager = new PageResult<ProductSku>(page);
		int count = productSkuMapper.countProductSkusByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(productSkuMapper.findProductSkusByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ProductSku insert(ProductSku productSku) {
		ProductSku result = this.save(productSku);
		return result;
	}

	@Override
	public List<ProductSku> findByProductSn(String productSn) {
		return productSkuMapper.findProductSkusByProductSn(productSn);
	}

	@Override
	public ProductSku findById(Long id) {
		return this.findOneById(id);
	}

	@Override
	public Date getLastSysDate() {
		return productSkuMapper.getLastSysDate();
	}

	@Override
	public void save(List<ProductSku> productSkus) {
	}

	@Override
	public void updateOnlineProductSku() {
		productSkuMapper.updateOnlineProductSku();
	}

	@Override
	public void insertOnlineProductSku() {
		productSkuMapper.insertOnlineProductSku();
	}

	@Override
	public void updateOfflineProductSku() {
		productSkuMapper.updateOfflineProductSku();
	}

	@Override
	public void insertOfflineProductSku() {
		productSkuMapper.insertOfflineProductSku();
	}

	@Override
	public void updateOnlineStatus() {
		productSkuMapper.updateOnlineStatus();
	}

	@Override
	public void updateOfflineStatus() {
		productSkuMapper.updateOfflineStatus();
	}

}

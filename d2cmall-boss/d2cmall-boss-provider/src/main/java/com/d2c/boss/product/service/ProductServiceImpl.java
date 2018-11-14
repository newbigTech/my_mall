package com.d2c.boss.product.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.product.dao.ProductMapper;
import com.d2c.boss.product.dao.ProductSkuMapper;
import com.d2c.boss.product.dto.ProductDto;
import com.d2c.boss.product.model.Product;
import com.d2c.boss.product.model.ProductSku;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.boss.sys.util.BeanUtils;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("productService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductServiceImpl extends ListServiceImpl<Product> implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductSkuMapper productSkuMapper;

	@Override
	public PageResult<Product> findProductsByQuery(PageModel page, ProQuery query) {

		PageResult<Product> pager = new PageResult<Product>(page);
		int count = productMapper.countProductsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(productMapper.findProductsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Product insert(Product product) {
		Product result = this.save(product);
		return result;
	}

	@Override
	public PageResult<ProductDto> findProductDtosByQuery(PageModel page, ProQuery query) {
		PageResult<ProductDto> pager = new PageResult<ProductDto>(page);
		int count = productMapper.countProductsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		} else {
			List<ProductDto> productDtos = new ArrayList<ProductDto>();
			List<Product> products = productMapper.findProductsByQuery(page, query);
			for (Product product : products) {
				ProductDto dto = new ProductDto();
				List<ProductSku> productSkus = productSkuMapper.findProductSkusByProductSn(product.getSn());
				BeanUtils.copyProperties(product, dto);
				dto.setProductSkus(productSkus);
				productDtos.add(dto);
			}
			pager.setList(productDtos);
		}
		return pager;
	}

	@Override
	public Product findById(Long id) {
		return this.findOneById(id);
	}

	@Override
	public Date getLastSysDate() {
		return productMapper.getLastSysDate();
	}

	@Override
	public void save(List<Product> products) {
	}

	@Override
	public void updateOnlineProduct() {
		productMapper.updateOnlineProduct();
	}

	@Override
	public void insertOnlineProduct() {
		productMapper.insertOnlineProduct();
	}

	@Override
	public void updateOfflineProduct() {
		productMapper.updateOfflineProduct();
	}

	@Override
	public void insertOfflineProduct() {
		productMapper.insertOfflineProduct();
	}

	@Override
	public void updateOnlineStatus() {
		productMapper.updateOnlineStatus();
	}

	@Override
	public void updateOfflineStatus() {
		productMapper.updateOfflineStatus();
	}
}

package com.d2c.boss.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.product.dao.ProductCategoryMapper;
import com.d2c.boss.product.model.ProductCategory;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("productCategoryService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductCategoryServiceImpl extends ListServiceImpl<ProductCategory> implements ProductCategoryService {

	@Autowired
	private ProductCategoryMapper productCategory;

	@Override
	public PageResult<ProductCategory> findProductCategorysByQuery(PageModel page, ProQuery query) {

		PageResult<ProductCategory> pager = new PageResult<ProductCategory>(page);
		int count = productCategory.countProductCategoryByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(productCategory.findProductCategoryByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ProductCategory insert(ProductCategory productCategory) {
		ProductCategory result = this.save(productCategory);
		return result;
	}

	@Override
	public void save(List<ProductCategory> productCategorys) {
	}

	@Override
	public void sendInfoToCrm() {
		productCategory.updateToCrm();
		productCategory.insertToCrm();
		productCategory.upDateStatus();
	}

}

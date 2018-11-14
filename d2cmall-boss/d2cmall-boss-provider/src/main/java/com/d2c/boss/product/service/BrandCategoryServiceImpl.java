package com.d2c.boss.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.product.dao.BrandCategoryMapper;
import com.d2c.boss.product.model.BrandCategory;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("brandCategoryService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BrandCategoryServiceImpl extends ListServiceImpl<BrandCategory> implements BrandCategoryService {

	@Autowired
	private BrandCategoryMapper brandCategoryMapper;

	@Override
	public PageResult<BrandCategory> findBrandCategorysByQuery(PageModel page, ProQuery query) {

		PageResult<BrandCategory> pager = new PageResult<BrandCategory>(page);
		int count = brandCategoryMapper.countBrandCategorysByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(brandCategoryMapper.findBrandCategorysByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public BrandCategory insert(BrandCategory brandCategory) {
		BrandCategory result = this.save(brandCategory);
		return result;
	}

}

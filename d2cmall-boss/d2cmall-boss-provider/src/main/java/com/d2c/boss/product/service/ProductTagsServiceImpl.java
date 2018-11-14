package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.product.dao.ProductTagsMapper;
import com.d2c.boss.product.model.ProductTags;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("productTagsService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductTagsServiceImpl extends ListServiceImpl<ProductTags> implements ProductTagsService {

	@Autowired
	private ProductTagsMapper productTagsMapper;

	@Override
	public PageResult<ProductTags> findProductTagsByQuery(PageModel page, ProQuery query) {
		PageResult<ProductTags> pager = new PageResult<ProductTags>(page);
		int count = productTagsMapper.countProductTagsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(productTagsMapper.findProductTagsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ProductTags insert(ProductTags productTags) {
		ProductTags result = this.save(productTags);
		return result;
	}

	@Override
	public Date getLastSysDate() {
		Date lastSysDate = productTagsMapper.getLastSysDate();
		return lastSysDate;
	}

	@Override
	public void save(List<ProductTags> productTags) {
	}

	@Override
	public ProductTags findById(Long id) {
		return this.findOneById(id);
	}
}

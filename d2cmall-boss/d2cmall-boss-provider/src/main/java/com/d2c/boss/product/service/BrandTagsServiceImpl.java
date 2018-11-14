package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.product.dao.BrandTagsMapper;
import com.d2c.boss.product.model.BrandTags;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("brandTagsService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BrandTagsServiceImpl extends ListServiceImpl<BrandTags> implements BrandTagsService {

	@Autowired
	private BrandTagsMapper brandTagsMapper;

	@Override
	public PageResult<BrandTags> findBrandTagsByQuery(PageModel page, ProQuery query) {
		PageResult<BrandTags> pager = new PageResult<BrandTags>(page);
		int count = brandTagsMapper.countBrandTagsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(brandTagsMapper.findBrandTagsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public BrandTags insert(BrandTags brandTags) {
		BrandTags result = this.save(brandTags);
		return result;
	}

	@Override
	public Date getLastSysDate() {
		Date lastSysDate = brandTagsMapper.getLastSysDate();
		return lastSysDate;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void save(List<BrandTags> brandTags) {
	}

	@Override
	public BrandTags findById(Long id) {
		return this.findOneById(id);
	}

}

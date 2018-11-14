package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.product.dao.BrandMapper;
import com.d2c.boss.product.model.Brand;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("brandService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BrandServiceImpl extends ListServiceImpl<Brand> implements BrandService {

	@Autowired
	private BrandMapper brandMapper;

	@Override
	public PageResult<Brand> findBrandsByQuery(PageModel page, ProQuery query) {

		PageResult<Brand> pager = new PageResult<Brand>(page);
		int count = brandMapper.countBrandsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(brandMapper.findBrandsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Brand insert(Brand brand) {
		Brand result = this.save(brand);
		return result;
	}

	@Override
	public Brand findById(Long id) {
		return this.findOneById(id);
	}

	@Override
	public Date getLastSysDate() {
		return brandMapper.getLastSysDate();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void save(List<Brand> brands) {
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateOnlineBrand() {
		brandMapper.updateOnlineBrand();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateOfflineBrand() {
		brandMapper.updateOfflineBrand();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void insertOnlineBrand() {
		brandMapper.insertOnlineBrand();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void insertOfflineBrand() {
		brandMapper.insertOfflineBrand();
	}

}

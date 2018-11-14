package com.d2c.boss.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.product.dao.ShopMapper;
import com.d2c.boss.product.model.Shop;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("shopService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ShopServiceImpl extends ListServiceImpl<Shop> implements ShopService {

	@Autowired
	private ShopMapper shopMapper;

	@Override
	public PageResult<Shop> findShopsByQuery(PageModel page, ProQuery query) {

		PageResult<Shop> pager = new PageResult<Shop>(page);
		int count = shopMapper.countShopsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(shopMapper.findShopsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Shop insert(Shop shop) {
		Shop result = this.save(shop);
		return result;
	}

	@Override
	public Shop findById(Long id) {
		return this.findOneById(id);
	}

	@Override
	public Date getLastSysDate() {
		Date lastSysDate = shopMapper.getLastSysDate();
		return lastSysDate;
	}

	@Override
	public void save(List<Shop> shops) {
	}

}

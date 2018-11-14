package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.online.dao.OnlineOrderItemMapper;
import com.d2c.boss.online.model.OnlineOrderItem;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("onlineOrderItemService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OnlineOrderItemServiceImpl extends ListServiceImpl<OnlineOrderItem> implements OnlineOrderItemService {
	@Autowired
	private OnlineOrderItemMapper onlineOrderItemMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void save(List<OnlineOrderItem> orderItems) {
	}

	@Override
	public Date getLastSysDate() {
		return onlineOrderItemMapper.getLastSysDate();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateStatus() {
		return onlineOrderItemMapper.updateStatus();
	}

	@Override
	public OnlineOrderItem findBySourceId(String sourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnlineOrderItem findById(Long id) {
		return this.findOneById(id);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void insertNewToOrderItems() {
		onlineOrderItemMapper.insertNewToOrderItems();
		onlineOrderItemMapper.updateStatus(1, 0);

	}

	@Override
	public PageResult<OnlineOrderItem> findModifyOnlineOrderItems(PageModel page) {
		PageResult<OnlineOrderItem> pager = new PageResult<OnlineOrderItem>(page);
		int count = onlineOrderItemMapper.countModifyOnlineOrderItems();
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(onlineOrderItemMapper.findModifyOnlineOrderItems(page));
		return pager;
	}
}

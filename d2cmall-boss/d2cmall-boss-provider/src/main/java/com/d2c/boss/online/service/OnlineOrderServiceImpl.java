package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.online.dao.OnlineOrderMapper;
import com.d2c.boss.online.model.OnlineOrder;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("onlineOrderService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OnlineOrderServiceImpl extends ListServiceImpl<OnlineOrder> implements OnlineOrderService {
	@Autowired
	private OnlineOrderMapper onlineOrderMapper;

	@Override
	public void save(List<OnlineOrder> orders) {
	}

	@Override
	public Date getLastSysDate() {
		Date lastSysDate = onlineOrderMapper.getLastSysDate();
		return lastSysDate;
	}

	@Override
	public OnlineOrder findByOrderSn(String orderSn) {
		return onlineOrderMapper.findByOrderSn(orderSn);
	}

	@Override
	public void insertNewToOrders() {
		onlineOrderMapper.insertToOrders();
		onlineOrderMapper.updateStatus(1, 0);

	}

	@Override
	public void upDateOldToOrders(OnlineOrder onlineOrder) {
		onlineOrderMapper.updateToOrdersByOrderSn(onlineOrder);
		Example example = new Example(null);
		Criteria criteria = example.createCriteria();
		criteria.andCondition("sn =" + onlineOrder.getSn());
		onlineOrderMapper.updateByConditionSelective(onlineOrder, example);
	}

	@Override
	public PageResult<OnlineOrder> findModifyOnlineOrders(PageModel page) {

		PageResult<OnlineOrder> pager = new PageResult<OnlineOrder>(page);
		int count = onlineOrderMapper.countModifyOnlineOrders();
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(onlineOrderMapper.findModifyOnlineOrders(page));
		return pager;
	}

	@Override
	public void upStatusByOrderSn(int status, String orderSn) {
		onlineOrderMapper.upStatusByOrderSn(status, orderSn);

	}

	@Override
	public void updateSource() {
		onlineOrderMapper.updateSourceNull();
		onlineOrderMapper.updateSourceIos();
		onlineOrderMapper.updateSourceAndroid();
	}

	@Override
	public void updateStatus() {
		onlineOrderMapper.updateStatus(0, 1);
	}

}

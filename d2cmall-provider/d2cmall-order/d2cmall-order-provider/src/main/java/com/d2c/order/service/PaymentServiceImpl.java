package com.d2c.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;
import com.d2c.order.dao.PaymentMapper;
import com.d2c.order.dto.AbnormalPaymentDto;
import com.d2c.order.model.Payment;
import com.d2c.order.query.AbnormalPaymentSearcher;

/**
 * @see 支付记录
 * 
 * @author xh
 */
@Service("paymentService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class PaymentServiceImpl extends ListServiceImpl<Payment> implements PaymentService {

	@Autowired
	private PaymentMapper paymentMapper;

	@Override
	public Payment findById(Long id) {
		return paymentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Payment findByAlipaySn(String alipaySn) {
		return paymentMapper.findByAlipaySn(alipaySn);
	}

	@Override
	public PageResult<Payment> findBySearcher(AbnormalPaymentSearcher searcher, PageModel page) {
		PageResult<Payment> pager = new PageResult<>(page);
		int totalCount = paymentMapper.countBySearcher(searcher);
		if (totalCount > 0) {
			List<Payment> list = paymentMapper.findBySearcher(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public PageResult<AbnormalPaymentDto> findAbnormalPayment(AbnormalPaymentSearcher searcher, PageModel page) {
		PageResult<AbnormalPaymentDto> pager = new PageResult<>(page);
		int totalCount = paymentMapper.countAbnormalPayment(searcher);
		if (totalCount > 0) {
			List<AbnormalPaymentDto> list = paymentMapper.findAbnormalPayment(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	@TxTransaction
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Payment insert(Payment payment) {
		return this.save(payment);
	}

	@Override
	public int updateNotNull(Payment entity) {
		return super.updateNotNull(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int doSuccess(Long id, Long memberId) {
		return paymentMapper.doSuccess(id, memberId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int doInvalid(Long paymentId) {
		return paymentMapper.doInvalid(paymentId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int doCancel(Long id) {
		return paymentMapper.doCancel(id);
	}

}

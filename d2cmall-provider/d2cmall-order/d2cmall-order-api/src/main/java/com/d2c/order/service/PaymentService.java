package com.d2c.order.service;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.order.dto.AbnormalPaymentDto;
import com.d2c.order.model.Payment;
import com.d2c.order.query.AbnormalPaymentSearcher;

public interface PaymentService {

	Payment findById(Long paymentId);

	Payment findByAlipaySn(String alipaySn);

	/**
	 * 根据条件查询
	 * 
	 * @param searcher
	 * @param pageModel
	 * @return
	 */
	PageResult<Payment> findBySearcher(AbnormalPaymentSearcher searcher, PageModel pageModel);

	/**
	 * 根据条件查找异常单
	 * 
	 * @param searcher
	 * @param pageModel
	 * @return
	 */
	PageResult<AbnormalPaymentDto> findAbnormalPayment(AbnormalPaymentSearcher searcher, PageModel pageModel);

	Payment insert(Payment payment);

	int doSuccess(Long paymentId, Long memberId);

	int doInvalid(Long paymentId);

	int doCancel(Long id);

}

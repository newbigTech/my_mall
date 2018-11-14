package com.d2c.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.dto.AbnormalPaymentDto;
import com.d2c.order.model.Payment;
import com.d2c.order.query.AbnormalPaymentSearcher;

public interface PaymentMapper extends SuperMapper<Payment> {

	Payment findByAlipaySn(String alipaySn);

	List<Payment> findBySearcher(@Param("searcher") AbnormalPaymentSearcher searcher, @Param("page") PageModel page);

	int countBySearcher(@Param("searcher") AbnormalPaymentSearcher searcher);

	List<AbnormalPaymentDto> findAbnormalPayment(@Param("searcher") AbnormalPaymentSearcher searcher,
			@Param("page") PageModel page);

	int countAbnormalPayment(@Param("searcher") AbnormalPaymentSearcher searcher);

	int doSuccess(@Param("id") Long id, @Param("memberId") Long memberId);

	int doInvalid(Long id);

	int doCancel(Long id);

}

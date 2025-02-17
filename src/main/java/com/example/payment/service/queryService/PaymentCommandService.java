package com.example.payment.service.queryService;

import static com.example.payment.common.exception.ErrorMessage.VALIDATE_PAYMENT_ERROR;

import com.example.payment.common.exception.ErrorMessage;
import com.example.payment.common.exception.LinkException;
import com.example.payment.common.exception.ValidateException;
import com.example.payment.domain.Order;
import com.example.payment.domain.Payment;
import com.example.payment.infrastructure.controller.dto.WebhookRequest;
import com.example.payment.repository.MemberRepository;
import com.example.payment.repository.OrderRepository;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.repository.PortOneHttpRepository;
import com.example.payment.repository.dto.PortOneCheckSinglePaymentResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 결제 CommandService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentCommandService {
	private final PaymentRepository paymentRepository;
	private final MemberRepository memberRepository;
	private final OrderRepository orderRepository;
	private final PortOneHttpRepository portOneHttpRepository;

	/**
	 * 결제 승인
	 * @param request (결제 번호, 주문 번호, 결제 결과, 취소 내역 아이디)
	 */
	public void approvePayment(WebhookRequest request) {

		PortOneCheckSinglePaymentResDto singlePayment = null;

		try {
			// 결제 내역 단건 조회 호출
			singlePayment = portOneHttpRepository.getPayment(request.getImpUid());
		} catch (Exception exception) {
			log.error("결제 내역 단건 조회 연동 실패");
			throw new LinkException(ErrorMessage.LINK_ERROR);
		}

		Order order = orderRepository.findById(request.getMerchantUid()).orElseThrow(() -> new ValidateException(VALIDATE_PAYMENT_ERROR));
		if (!order.isPossiblePayment(singlePayment.getAmount())) {
			throw new ValidateException(VALIDATE_PAYMENT_ERROR);
		}

		Payment payment = new Payment(singlePayment.getImp_uid(), 4000);
		paymentRepository.save(payment);
	}
}

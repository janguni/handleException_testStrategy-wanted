package com.example.payment.service;

import com.example.payment.common.exception.ErrorMessage;
import com.example.payment.common.exception.LinkException;
import com.example.payment.controller.dto.WebhookRequest;
import com.example.payment.domain.Order;
import com.example.payment.domain.Payment;
import com.example.payment.repository.MemberRepository;
import com.example.payment.repository.OrderRepository;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.repository.PortOneHttpRepository;
import com.example.payment.repository.dto.PortOneCheckSinglePaymentResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
	private final PaymentRepository paymentRepository;
	private final MemberRepository memberRepository;
	private final OrderRepository orderRepository;
	private final PortOneHttpRepository portOneHttpRepository;

	public void approvePayment(WebhookRequest request) {

		PortOneCheckSinglePaymentResponse result = null;

		try {
			// 결제내역 단건 조회 호출
			result = portOneHttpRepository.getPayment(request.getImpUid());
			log.info("result: {}", result);
		} catch (Exception exception) {
			log.info("결제내역 단건 조회 연동 실패");
			throw new LinkException(ErrorMessage.LINK_ERROR);
		}

		if (!validateApprovePayment(request, result)) {
			// TODO: 결제 취소
			// 만약 결제 취소하다가 실패하면, 어떻게 롤백을 해야하는가...?

			// TODO: ApiException 발생

		}

		Payment payment = new Payment(request.getImpUid(), 3000);
		paymentRepository.save(payment);

	}

	private boolean validateApprovePayment(WebhookRequest request, PortOneCheckSinglePaymentResponse response) {
		boolean isPass = true;	// 검증 성공 여부

		Optional<Order> optionalOrder = orderRepository.findById(request.getMerchantUid());
		if (optionalOrder.isEmpty()) {
			isPass = false;
		}

		// 금액 검증
//		if (! response.getAmount().equals(optionalOrder.get().getTotalAmount())) {
//			isPass = false;
//		}
		return isPass;
	}

}

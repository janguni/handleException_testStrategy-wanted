package com.example.payment.service;

import com.example.payment.controller.dto.PaymentRequest;
import com.example.payment.domain.Card;
import com.example.payment.domain.Member;
import com.example.payment.domain.Payment;
import com.example.payment.repository.CardRepository;
import com.example.payment.repository.MemberRepository;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.repository.dto.PortOnePaymentRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final MemberRepository memberRepository;
	private final PGService pgService;

	public void processPay(PaymentRequest request) {
		// TODO: 사용자 조회 실패 시 오류 구체화
		Member member = memberRepository.findById(request.getMemberId())
			.orElseThrow(() -> new RuntimeException());

		Card card = member.getCard();
		if (card == null) {
			new RuntimeException();
		}

		// TODO: pg사별로 mapper 필요
		PortOnePaymentRequest portOneRequest = PortOnePaymentRequest.builder()
			.merchant_uid(request.getOrderId())
			.amount(request.amount)
			.cardNumber(card.getCardNumber())
			.expiry(card.getExpiry())
			.pwd2Digit(card.getPwd2Digit())
			.cardQuota(request.installments)
			.interestFreeByMerchant(request.isInterestFreeInstallment)
			.useCardPoint(request.getIsUseCardPoint())
			.build();

		Object response = pgService.requestPayment(portOneRequest);

		// TODO: payment 생성
		Payment payment = new Payment();
		try {
			paymentRepository.save(payment);
		} catch (Exception e) {
			// TODO: 결제 취소 요청
		}
	}
}

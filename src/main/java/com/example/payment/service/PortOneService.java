package com.example.payment.service;

import com.example.payment.repository.PortOneHttpRepository;
import com.example.payment.repository.dto.PortOnePaymentRequest;
import com.example.payment.repository.dto.PortOnePaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortOneService implements PGService {

	private final PortOneHttpRepository portOneHttpRepository;

	@Override
	public Object requestPayment(Object paymentRequest) {
		try {
			Object response = portOneHttpRepository.requestPayment(
				(PortOnePaymentRequest) paymentRequest);

			// TODO: 정상 응답 확인, 결제 성공 확인
			// new RuntimeException("결제 실패")
		} catch (Exception e) { // TODO: exception 구체화 필요
			new RuntimeException("연동실패");
		}
		return null;
	}
}

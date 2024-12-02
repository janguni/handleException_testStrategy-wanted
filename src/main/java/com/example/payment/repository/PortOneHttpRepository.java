package com.example.payment.repository;

import com.example.payment.repository.dto.PortOnePaymentRequest;
import com.example.payment.repository.dto.PortOnePaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PortOneHttpRepository {
	public Object requestPayment(PortOnePaymentRequest request) {
		// TODO: reginClient 사용해서 요청 보내기
		PortOnePaymentResponse response = null;
		return response;
	}
}

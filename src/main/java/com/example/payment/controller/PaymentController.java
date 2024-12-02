package com.example.payment.controller;

import com.example.payment.controller.dto.PaymentRequest;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	public ResponseEntity pay(@RequestBody PaymentRequest request) {

		paymentService.processPay(request);
		// TODO: 응답 정보를 어떤 걸 주어야 하나?
		return null;
	}
}

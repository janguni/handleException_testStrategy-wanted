package com.example.payment.infrastructure.controller;

import com.example.payment.infrastructure.controller.dto.WebhookRequest;
import com.example.payment.service.queryService.PaymentCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WebhookController {

	private final PaymentCommandService paymentCommandService;

	@PostMapping("/portone-webhook")
	public ResponseEntity handleEvent(@RequestBody WebhookRequest request) {
		switch (request.getStatus()) {
			case "paid":
				paymentCommandService.approvePayment(request);
				break;
			default:
				break;
		}
		return null;
	}
}

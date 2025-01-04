package com.example.payment.controller;

import com.example.payment.controller.dto.WebhookRequest;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WebhookController {
	private final PaymentService paymentService;


	@PostMapping("/portone-webhook")
	public ResponseEntity handleEvent(@RequestBody WebhookRequest request) {
		switch (request.getStatus()) {
			case "paid":
				paymentService.approvePayment(request);
				break;
			default:
				break;
		}
		return null;
	}
}

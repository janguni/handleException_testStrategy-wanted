package com.example.payment.infrastructure.controller;

import static com.example.payment.common.constant.ApiUrl.EVENT_FROM_WEBHOOK;

import com.example.payment.infrastructure.controller.dto.request.WebhookRequest;
import com.example.payment.service.command.dto.PaymentApproveCommand;
import com.example.payment.service.query.queryService.PaymentCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 포트원 웹훅으로부터 온 이벤트 Controller
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class EventHandleController {

	private final PaymentCommandService paymentCommandService;

	@PostMapping(EVENT_FROM_WEBHOOK)
	public ResponseEntity handleEvent(@RequestBody WebhookRequest request) {
		switch (request.getStatus()) {
			case "paid":
				paymentCommandService.approvePayment(new PaymentApproveCommand(request));
				break;
			default:
				break;
		}
		return null;
	}
}

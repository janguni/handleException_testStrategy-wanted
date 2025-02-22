package com.example.payment.service.command.dto;

import com.example.payment.infrastructure.controller.dto.request.WebhookRequest;
import lombok.Getter;
import lombok.ToString;

/**
 * 결제 승인 Command (웹훅 이벤트 요청에 의한)
 */
@Getter
@ToString
public class PaymentApproveCommand {
	private final String paymentNo;		// 결제 번호
	private final String orderNo;		// 주문 번호

	public PaymentApproveCommand(WebhookRequest request) {
		this.paymentNo = request.getImpUid();
		this.orderNo = request.getMerchantUid();
	}
}

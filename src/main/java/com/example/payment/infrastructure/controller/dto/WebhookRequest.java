package com.example.payment.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WebhookRequest {
	private String impUid;			// 결제번호
	private String merchantUid;		// 주문번호
	private String status;			// 결제 결과 (paid, cancelled, ready, failed)
	private String cancellationId;	// 취소내역 아이디
}

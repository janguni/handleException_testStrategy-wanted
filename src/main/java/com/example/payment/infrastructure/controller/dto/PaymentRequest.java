package com.example.payment.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentRequest {
	public Long memberId;
	public String orderId;
	public Integer amount;
	public Integer installments; // 할부 개월
	public Boolean isInterestFreeInstallment; // 무이자 할부 여부
	public Boolean isUseCardPoint; // 카드포인트 사용여부
}

package com.example.payment.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortOneCheckSinglePaymentResDto {
	private String imp_uid;			// 결제 번호
	private String merchant_uid;	// 주문 번호
	private Integer amount;			// 결제 금액
	private Integer cancelAmount;	// 취소 금액
	private String currency;		// 결제통화 구분코드
	private String status;			// 결제 상태
}

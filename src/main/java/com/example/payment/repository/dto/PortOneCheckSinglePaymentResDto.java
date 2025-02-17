package com.example.payment.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortOneCheckSinglePaymentResDto {
	private String imp_uid;
	private String merchant_uid;
	private Integer amount;
	private Integer cancelAmount;
	private String currency;		// 결제통화 구분코드
	private String status;
}

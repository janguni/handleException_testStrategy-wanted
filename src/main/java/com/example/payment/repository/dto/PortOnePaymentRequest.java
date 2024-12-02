package com.example.payment.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortOnePaymentRequest {
	private String merchant_uid;
	private int amount;
	private String cardNumber;
	private String expiry;
	private String pwd2Digit;
	private Integer cardQuota;
	private Boolean interestFreeByMerchant;
	private Boolean useCardPoint;
}

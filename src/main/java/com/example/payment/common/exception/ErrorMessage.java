package com.example.payment.common.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {

	LINK_ERROR("외부연동에 실패했습니다."),
	VALIDATE_PAYMENT_ERROR("결제 검증 중 오류가 발생했습니다.");

	private String message;

	ErrorMessage(String message) {
		this.message = message;
	}
}

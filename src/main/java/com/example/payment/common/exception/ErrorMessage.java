package com.example.payment.common.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {

	LINK_ERROR("외부연동에 실패했습니다.");

	private String message;

	ErrorMessage(String message) {
		this.message = message;
	}
}

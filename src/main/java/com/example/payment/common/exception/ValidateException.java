package com.example.payment.common.exception;

public class ValidateException extends RuntimeException {

	private ErrorMessage errorMessage;

	public ValidateException() {}

	public ValidateException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
	}
}

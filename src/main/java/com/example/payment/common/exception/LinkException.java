package com.example.payment.common.exception;

/**
 * 외부연동으로 인한 예외는 IOException (runtimeException이 아님)
 * 하지만 프레임워크에서는 보통 checkedException보다는 uncheckedException을 주로 사용 (try-catch너무 많아짐, controllerAdvice의 편의성 등에 의한 이유로)
 * 그래서 나도 try ,catch로 다 잡는건 불필요하다고 생각이 들어 runtimeException 으로 처리할거임
 */
public class LinkException extends RuntimeException { // 실행 중 발생하는 예외는 RuntimeException을 상속해야함.

	private ErrorMessage errorMessage;

	public LinkException() {}

	public LinkException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
	}
}

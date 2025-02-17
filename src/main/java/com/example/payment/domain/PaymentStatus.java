package com.example.payment.domain;

public enum PaymentStatus {
	WAITING {
		public boolean isPossible() {
			return false;
		}
	};
	public abstract boolean isPossible();

}

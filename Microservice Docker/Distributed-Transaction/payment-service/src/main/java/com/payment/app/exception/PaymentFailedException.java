package com.payment.app.exception;

public class PaymentFailedException extends RuntimeException {

	public PaymentFailedException() {
		super("Payment Failed ");
	}

	public PaymentFailedException(String msg) {
		super(msg);
	}
}

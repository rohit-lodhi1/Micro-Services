package com.order.app.exception;

public class ErrorWhileProcessing extends RuntimeException{

	public ErrorWhileProcessing() {
		super("Error while Processing request");
	}
	
	public ErrorWhileProcessing(String msg) {
		super(msg);
	}
}

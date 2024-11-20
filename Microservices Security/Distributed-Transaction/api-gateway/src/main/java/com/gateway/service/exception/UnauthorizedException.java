package com.api.gateway.exception;

public class UnauthorizedException extends RuntimeException {

	public UnauthorizedException() {
		super("Unauthorized Access");
	}

	public UnauthorizedException(String msg) {
		super(msg);
	}
}

package com.arphoenix.zipkin.demozipkin.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2241159604261303730L;

	public UnauthorizedException() {
	}

	public UnauthorizedException(String message) {
		super(message);
	}
}
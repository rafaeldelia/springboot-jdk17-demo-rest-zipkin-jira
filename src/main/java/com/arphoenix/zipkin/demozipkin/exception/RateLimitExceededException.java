package com.arphoenix.zipkin.demozipkin.exception;

public class RateLimitExceededException extends RuntimeException {

	private static final long serialVersionUID = -3750648470210784635L;

	public RateLimitExceededException() {
		super();
	}

	public RateLimitExceededException(String msg) {
		super(msg);
	}
}
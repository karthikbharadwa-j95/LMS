package com.te.lms.exception;

public class LmsException extends RuntimeException {

	private String message;

	@Override
	public String getMessage() {
		return this.message = message;
	}

	public LmsException(String message) {
		this.message = message;
	}

}

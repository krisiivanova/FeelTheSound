package com.feelthesound.model.exceptions;

public class LikeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LikeException() {
		super();
	}

	public LikeException(String message, Throwable cause) {
		super(message, cause);
	}

	public LikeException(String message) {
		super(message);
	}

	public LikeException(Throwable cause) {
		super(cause);
	}
}

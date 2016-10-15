package com.feelthesound.model.exceptions;

public class SongException extends Exception {

	private static final long serialVersionUID = 1L;

	public SongException() {
		super();
	}

	public SongException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SongException(String arg0) {
		super(arg0);
	}

	public SongException(Throwable arg0) {
		super(arg0);
	}
}

package com.feelthesound.model.exceptions;

public class PlaylistException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlaylistException() {
		super();
	}

	public PlaylistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PlaylistException(String arg0) {
		super(arg0);
	}

	public PlaylistException(Throwable arg0) {
		super(arg0);
	}	
}

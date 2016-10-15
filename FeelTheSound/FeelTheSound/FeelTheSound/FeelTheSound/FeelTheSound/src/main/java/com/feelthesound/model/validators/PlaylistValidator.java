package com.feelthesound.model.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaylistValidator {
	private Pattern pattern;
	private Matcher matcher;

	public static final String PLAYLIST_MESSAGE = "Your playlist name must contain only letters and must be between 3-30 characters long";
	private static final String PlAYLIST_PATTERN = "^[a-zA-Z]{3,30}$";

	public PlaylistValidator() {
		pattern = Pattern.compile(PlAYLIST_PATTERN);
	}

	public boolean validate(final String password) {
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
}

package com.feelthesound.model.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaylistValidator {
	private Pattern pattern;
	private Matcher matcher;

	public static final String PLAYLIST_MESSAGE = "Your playlist name must contain only letters with no more than one space between them and must be between 3 and 30 characters long.";
	private static final String PlAYLIST_PATTERN = "^([A-Za-z]+ )+[A-Za-z]+$|^[A-Za-z]{3,30}$";

	public PlaylistValidator() {
		pattern = Pattern.compile(PlAYLIST_PATTERN);
	}

	public boolean validate(final String password) {
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
}

package com.feelthesound.model.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {
	private Pattern pattern;
	private Matcher matcher;
	
	public static final String USERNAME_MESSAGE = "Your username must contain only small letters, 0-9, _, - and must be between 3-15 symbols.";
	private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

	public UsernameValidator() {
		pattern = Pattern.compile(USERNAME_PATTERN);
	}

	public boolean validate(final String username) {
		matcher = pattern.matcher(username);
		return matcher.matches();

	}
}
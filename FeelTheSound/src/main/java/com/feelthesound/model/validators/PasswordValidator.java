package com.feelthesound.model.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	private Pattern pattern;
	private Matcher matcher;

	public static final String PASSWORD_MESSAGE = "Your password must cointain at least 1 digit, 1 lowercase, 1 uppercase, no spaces and must be between 6-13 symbols.";
	private static final String PASSWORD_PATTERN = "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=\\S+$)"
			+ ".{6,13}";

	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	public boolean validate(final String password) {
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
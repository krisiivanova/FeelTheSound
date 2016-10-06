package com.feelthesound.model.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	private  Pattern pattern;
	private  Matcher matcher;

	// at least one digit, at least one lower case, at least one upper case, no
	// spaces, between 6-13 chars
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
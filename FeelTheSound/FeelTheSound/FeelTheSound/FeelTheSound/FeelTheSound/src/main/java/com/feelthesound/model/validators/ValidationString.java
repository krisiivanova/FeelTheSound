package com.feelthesound.model.validators;

public class ValidationString {

	public static boolean isValidString(String string) {
		if (string != null && string.trim().length() > 0) {
			return true;
		}
		return false;
	}
}

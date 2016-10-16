package com.feelthesound.model.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongValidator {
	private Pattern pattern;
	private Matcher matcher;
	
	public static final String SONG_MESSAGE = "Song name, artist and genre must be between 2 and 30 characters long.";
	private static final String SONG_PATTERN = "^\\s*${2,30}$";

	public SongValidator() {
		pattern = Pattern.compile(SONG_PATTERN);
	}

	public boolean validate(final String song) {
		matcher = pattern.matcher(song);
		return matcher.matches();
	}
}

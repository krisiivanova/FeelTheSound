package com.feelthesound.model;

import java.time.LocalDateTime;

public interface ISong {

	String getName();

	String getArtist();

	int getId();

	int getUploaderId();

	String getGenre();

	LocalDateTime getUploadDate();

	String getSongPath();

}
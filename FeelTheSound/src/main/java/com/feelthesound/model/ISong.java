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

	int getLikes();

	void addLike(Like like);

	void removeLike(int userId);

	boolean likedBy(int userId);

}
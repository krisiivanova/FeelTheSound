package com.feelthesound.model;

import java.time.LocalDateTime;

import com.feelthesound.model.exceptions.SongException;
import com.feelthesound.model.validators.*;

public class Song {
	private int id;
	private String name;
	private String artist;
	private LocalDateTime uploadDate;
	private String genre;
	private int userId;

	public Song(int id, String name, String artist, String genre, int userId) throws SongException {
		this.setId(id);
		this.setName(name);
		this.setArtist(artist);
		this.setUserId(userId);
		this.setUploadDate(uploadDate);
		this.setGenre(genre);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws SongException {
		if (ValidationString.isValidString(name)) {
			this.name = name;
		} else {
			throw new SongException("Invalid song name!");
		}
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) throws SongException {
		if (ValidationString.isValidString(artist)) {
			this.artist = artist;
		} else {
			throw new SongException("Invalid artist name!");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) throws SongException {
		if (ValidationString.isValidString(genre)) {
			this.genre = genre;
		} else {
			throw new SongException("Invalid genre name!");
		}
	}

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = LocalDateTime.now();
	}
}

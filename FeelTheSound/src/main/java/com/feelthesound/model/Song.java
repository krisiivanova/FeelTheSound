package com.feelthesound.model;

import java.time.LocalDateTime;

import com.feelthesound.model.validators.ValidationString;

public class Song implements Comparable<Song>, ISong {
	private int id;
	private String name;
	private String artist;
	private LocalDateTime uploadDate;
	private String genre;
	private int uploaderId;
	private String songPath;

	public Song(int id, String name, String artist, String genre, int userId, String songPath) {
		this.setId(id);
		this.setName(name);
		this.setArtist(artist);
		this.setUserId(userId);
		this.setUploadDate(uploadDate);
		this.setGenre(genre);
		this.setSongPath(songPath);
	}

	public Song(String name, String artist, String songPath) {
		this.setName(name);
		this.setArtist(artist);
		this.setGenre(genre);
		this.setSongPath(songPath);
		this.setUploadDate(uploadDate);
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name){
		if (ValidationString.isValidString(name)) {
			this.name = name;
		}

	}

	@Override
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist){
		if (ValidationString.isValidString(artist)) {
			this.artist = artist;
		}
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id){
		if (id > 0) {
			this.id = id;
		}
	}

	@Override
	public int getUploaderId() {
		return uploaderId;
	}

	public void setUserId(int uploaderId){
		if (uploaderId > 0) {
			this.uploaderId = uploaderId;
		}
	}

	@Override
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre){
		if (ValidationString.isValidString(genre)) {
			this.genre = genre;
		}
	}

	@Override
	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = LocalDateTime.now();
	}

	@Override
	public String getSongPath() {
		return songPath;
	}

	public void setSongPath(String songPath){
		if (ValidationString.isValidString(songPath)) {
			this.songPath = songPath;
		}
	}

	@Override
	public int compareTo(Song song) {
		return -this.uploadDate.compareTo(song.getUploadDate());
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", artist=" + artist + ", uploadDate=" + uploadDate + ", genre="
				+ genre + ", uploaderId=" + uploaderId + ", songPath=" + songPath + "]";
	}
}

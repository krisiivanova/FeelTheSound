package com.feelthesound.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.feelthesound.model.validators.ValidationString;

public class Song implements Comparable<Song> {
	private int id;
	private int userId;
	private String songPath;
	private String name;
	private String artist;
	private String genre;
	private Date uploadDate;
	private List<Like> likes;

	public Song(int id, int userId, String songPath, String name, String artist, String genre, Date upload) {
		this.setId(id);
		this.setUserId(userId);
		this.setSongPath(songPath);
		this.setName(name);
		this.setArtist(artist);
		this.setUserId(userId);
		this.setUploadDate(uploadDate);
		this.setGenre(genre);
		this.setUploadDate(upload);
		this.likes = new ArrayList<Like>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (ValidationString.isValidString(name)) {
			this.name = name;
		}
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		if (ValidationString.isValidString(artist)) {
			this.artist = artist;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 0) {
			this.id = id;
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		if (id > 0) {
			this.userId = userId;
		}
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if (ValidationString.isValidString(genre)) {
			this.genre = genre;
		}
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getSongPath() {
		return songPath;
	}

	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}

	public int getLikes() {
		return likes.size();
	}

	@Override
	public int compareTo(Song song) {
		return -this.uploadDate.compareTo(song.getUploadDate());
	}

	public void addLike(Like like) {
		if (like != null) {
			likes.add(like);
		}
	}

	public void removeLike(int userId) {
		for (Like like : likes) {
			if (like.getUserId() == userId) {
				likes.remove(like);
				return;
			}
		}
	}

	public boolean likedBy(int userId) {
		if (likes == null) {
			return false;
		}

		for (Like like : likes) {
			if (like.getUserId() == userId) {
				return true;
			}
		}

		return false;
	}

}

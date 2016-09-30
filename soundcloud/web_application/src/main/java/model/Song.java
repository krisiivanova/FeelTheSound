package model;

import java.time.LocalDateTime;

public class Song {
	private int id;
	private String name;
	private String artist;
	private LocalDateTime uploadDate;
	private int userId;
	
	public Song(int id, String name, String artist, int userId){
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.uploadDate = LocalDateTime.now();
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
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
	
	
}

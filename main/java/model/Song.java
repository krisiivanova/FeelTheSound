package model;

import java.time.LocalDateTime;

public class Song {
	private String name;
	private String artist;
	private LocalDateTime uploadDate;
	
	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
		this.uploadDate = LocalDateTime.now();
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

}

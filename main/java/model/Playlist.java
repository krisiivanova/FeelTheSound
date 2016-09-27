package model;

import java.util.HashSet;

public class Playlist {
	private String name;
	private HashSet<Song> songs = new HashSet<Song>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}

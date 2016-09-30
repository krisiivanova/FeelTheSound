package model;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Playlist {
	private int id;
	private String name;
	private Set<Song> songs;
	private int userId;
	
	
	public Playlist(int id, String name, int userId) {
		this.id = id;
		this.name = name;
		this.songs = new TreeSet<Song>((s1, s2) -> s1.getId() - s2.getId());
		this.userId = userId;
	}
	
	public Set<Song> getSongs() {
		return Collections.unmodifiableSet(songs);
	}
	
	public void addSong(Song song) {
		if (song != null) {
			this.songs.add(song);
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}

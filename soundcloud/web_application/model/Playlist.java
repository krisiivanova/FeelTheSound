package com.soundcloud.model;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import com.soundcloud.model.exceptions.SongException;
import com.soundcloud.validators.ValidationString;

public class Playlist {
	private int id;
	private String name;
	private int userId;
	private Set<Song> songs;

	public Playlist(int id, String name, int userId) throws SongException {
		this.setId(id);
		this.setName(name);
		this.setUserId(userId);
		this.songs = new TreeSet<Song>((s1, s2) -> s1.getId() - s2.getId());
	}

	public void addSong(Song song) {
		if (song != null) {
			this.songs.add(song);
		}
	}

	public Set<Song> getSongs() {
		return Collections.unmodifiableSet(songs);
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

	public void setName(String name) throws SongException {
		if (ValidationString.isValidString(name)) {
			this.name = name;
		} else {
			throw new SongException("Invalid song name!");
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}

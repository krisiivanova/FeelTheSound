package com.feelthesound.model;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;
import com.feelthesound.model.validators.ValidationString;

@Component
public class Playlist implements IPlaylist {
	private int id;
	private String name;
	private int userId;
	private Set<Song> songs;

	public Playlist() {
	
	}

	public Playlist(int id, String name, int userId) {
		this.setId(id);
		this.setName(name);
		this.setUserId(userId);
		this.songs = new TreeSet<Song>((s1, s2) -> s1.getId() - s2.getId());
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.IPlaylist#addSong(com.feelthesound.model.Song)
	 */
	@Override
	public void addSong(Song song) {
		if (song != null) {
			this.songs.add(song);
		}
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.IPlaylist#getSongs()
	 */
	@Override
	public Set<Song> getSongs() {
		return Collections.unmodifiableSet(songs);
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.IPlaylist#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 0) {
			this.id = id;
		}
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.IPlaylist#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (ValidationString.isValidString(name)) {
			this.name = name;
		}
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.IPlaylist#getUserId()
	 */
	@Override
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		if (id > 0) {
			this.userId = userId;
		}
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", userId=" + userId + "]";
	}
}

package com.feelthesound.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.feelthesound.model.validators.ValidationString;

public class Song implements Comparable<Song>, ISong {
	private int id;
	private String name;
	private String artist;
	private LocalDateTime uploadDate;
	private String genre;
	private int uploaderId;
	private String songPath;
	private List<Like> likes;
	
	public Song(int id, String name, String artist, String genre, int userId, String songPath){
		this.setId(id);
		this.setName(name);
		this.setArtist(artist);
		this.setUserId(userId);
		this.setUploadDate(uploadDate);
		this.setGenre(genre);
		this.setSongPath(songPath);
		this.likes = new ArrayList<Like>();
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ISong#getName()
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
	 * @see com.feelthesound.model.ISong#getArtist()
	 */
	@Override
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		if (ValidationString.isValidString(artist)) {
			this.artist = artist;
		}
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ISong#getId()
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
	 * @see com.feelthesound.model.ISong#getUploaderId()
	 */
	@Override
	public int getUploaderId() {
		return uploaderId;
	}

	public void setUserId(int uploaderId) {
		if (uploaderId > 0) {
			this.uploaderId = uploaderId;
		}
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ISong#getGenre()
	 */
	@Override
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if (ValidationString.isValidString(genre)) {
			this.genre = genre;
		}
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ISong#getUploadDate()
	 */
	@Override
	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = LocalDateTime.now();
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ISong#getSongPath()
	 */
	@Override
	public String getSongPath() {
		return songPath;
	}

	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ISong#getLikes()
	 */
	@Override
	public int getLikes() {
		return likes.size();
	}

	@Override
	public int compareTo(Song song) {
		return -this.uploadDate.compareTo(song.getUploadDate());
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ISong#addLike(com.feelthesound.model.Like)
	 */
	@Override
	public void addLike(Like like) {
		if (like != null) {
			likes.add(like);
		}
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ISong#removeLike(int)
	 */
	@Override
	public void removeLike(int userId) {
		for (ILike like : likes) {
			if (like.getUserId() == userId) {
				likes.remove(like);
				return;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ISong#likedBy(int)
	 */
	@Override
	public boolean likedBy(int userId) {
		if (likes == null) {
			return false;
		}

		for (ILike like : likes) {
			if (like.getUserId() == userId) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", artist=" + artist + ", uploadDate=" + uploadDate + ", genre="
				+ genre + ", uploaderId=" + uploaderId + ", songPath=" + songPath + ", likes=" + likes + "]";
	}
}

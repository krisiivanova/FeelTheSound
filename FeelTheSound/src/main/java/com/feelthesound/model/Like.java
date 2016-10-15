package com.feelthesound.model;

public class Like implements ILike {

	private int songId;
	private int userId;

	public Like(int songId, int userId) {
		if (songId > 0) {
			this.songId = songId;
		} 

		if (userId > 0) {
			this.userId = userId;
		}
	}

	@Override
	public int getSongId() {
		return songId;
	}

	@Override
	public int getUserId() {
		return userId;
	}
}

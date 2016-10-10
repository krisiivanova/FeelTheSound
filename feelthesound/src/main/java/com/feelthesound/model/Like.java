package com.feelthesound.model;

public class Like {
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

	public int getSongId() {
		return songId;
	}

	public int getUserId() {
		return userId;
	}
}

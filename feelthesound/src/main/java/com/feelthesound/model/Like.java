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

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ILike#getSongId()
	 */
	@Override
	public int getSongId() {
		return songId;
	}

	/* (non-Javadoc)
	 * @see com.feelthesound.model.ILike#getUserId()
	 */
	@Override
	public int getUserId() {
		return userId;
	}
}

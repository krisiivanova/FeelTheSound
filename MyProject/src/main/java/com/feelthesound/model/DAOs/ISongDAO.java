package com.feelthesound.model.DAOs;

import com.feelthesound.model.Song;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.SongException;
import com.feelthesound.model.exceptions.UserException;

public interface ISongDAO {

	int insertSong(Song song) throws ConnectionException, SongException;

	void deleteSong(Song song) throws UserException, ConnectionException;

	int getSongLikes(Song song) throws ConnectionException;

	void likeSong(int userId, int songId) throws ConnectionException;

}
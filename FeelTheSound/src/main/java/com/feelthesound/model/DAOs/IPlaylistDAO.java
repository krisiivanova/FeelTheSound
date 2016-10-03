package com.feelthesound.model.DAOs;

import com.feelthesound.model.Playlist;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.UserException;

public interface IPlaylistDAO {

	int addNewPlaylist(Playlist playlist) throws UserException, ConnectionException;

	void addSongIntoPlaylist(Playlist playlist, int songId) throws ConnectionException;

}
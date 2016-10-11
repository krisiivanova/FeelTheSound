package com.feelthesound.model.DAOs;

import java.util.Set;

import com.feelthesound.model.exceptions.PlaylistException;

public interface IPlaylistDAO {

	Set<String> getAllPlaylistsNamesByUser(int userId) throws PlaylistException;
 
	int addPlaylist(String title, int userId) throws PlaylistException;

	int addSongInPlaylist(int playlistId, int songId) throws PlaylistException;
}
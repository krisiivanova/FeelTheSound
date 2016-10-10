package com.feelthesound.model.DAOs;

import java.util.Set;

public interface IPlaylistDAO {

	// getting all playlists by user
	Set<String> getAllPlaylistsNamesByUser(int userId);

	//creating new playlist 
	int addPlaylist(String title, int userId);

	//adding song the a playlist 
	int addSongInPlaylist(int playlistId, int songId);

}
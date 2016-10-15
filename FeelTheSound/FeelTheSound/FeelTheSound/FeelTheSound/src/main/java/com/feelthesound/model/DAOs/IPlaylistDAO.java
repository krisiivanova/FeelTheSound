package com.feelthesound.model.DAOs;

import java.util.List;

import com.feelthesound.model.IPlaylist;
import com.feelthesound.model.IUser;
import com.feelthesound.model.User;
import com.feelthesound.model.exceptions.PlaylistException;

public interface IPlaylistDAO {

	List<IPlaylist> getAllPlaylists(Integer userId);

	IPlaylist addPlaylist(String name, IUser user) throws PlaylistException;

	boolean ifPlaylistExist(User user, String name);

	void addSongInPlaylist(Integer playlistId, Integer songId) throws PlaylistException;

}
package com.feelthesound.model.DAOs;

import java.util.List;

import com.feelthesound.model.IPlaylist;
import com.feelthesound.model.IUser;
import com.feelthesound.model.User;
import com.feelthesound.model.exceptions.PlaylistException;
import com.feelthesound.model.exceptions.UserException;

public interface IPlaylistDAO {

	List<IPlaylist> getAllPlaylists(Integer userId) throws PlaylistException;

	IPlaylist addPlaylist(String name, IUser user) throws PlaylistException, UserException;

	boolean ifPlaylistExist(User user, String name) throws PlaylistException, UserException;

	void addSongInPlaylist(Integer playlistId, Integer songId) throws PlaylistException;

	void deletePlaylist(int userId, int playlistId) throws PlaylistException;

	List<Integer> getPlaylistSongs(int playlistId) throws PlaylistException;

}
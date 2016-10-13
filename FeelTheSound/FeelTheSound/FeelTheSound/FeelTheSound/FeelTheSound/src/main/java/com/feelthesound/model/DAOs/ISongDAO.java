package com.feelthesound.model.DAOs;

import java.util.List;

import com.feelthesound.model.ISong;
import com.feelthesound.model.exceptions.SongException;

public interface ISongDAO {

	int insertSong(ISong song) throws SongException;

	void deleteSong(ISong song) throws SongException;

	List<ISong> getSongsByUser(Integer userId);

	List<ISong> getSongsBySearchText(String searchText);

	List<ISong> getUserLikedSongs(Integer userId);

	ISong getLastAdded();

}
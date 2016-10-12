package com.feelthesound.model.DAOs;

import java.util.List;
import java.util.Map;

import com.feelthesound.model.ISong;
import com.feelthesound.model.Song;
import com.feelthesound.model.exceptions.SongException;

public interface ISongDAO {

	int insertSong(ISong song) throws SongException;

	void deleteSong(ISong song) throws SongException;

	Map<String, ISong> getAllSongs();
	
	List<ISong> getAllSongsWithPrefix(String prefix);
	
	List<ISong> getSongsByUser(Integer userId);
	
	List<ISong> getSongsBySearchText(String searchText);

}
package com.feelthesound.model.DAOs;

import java.util.Date;
import java.util.List;

import com.feelthesound.model.Song;

public interface ISongDAO {
	
	List<Song> getAllSongs();

	int insertSong(int userId, String songPath, String name, String artist, String genre, Date uploadDate);

}
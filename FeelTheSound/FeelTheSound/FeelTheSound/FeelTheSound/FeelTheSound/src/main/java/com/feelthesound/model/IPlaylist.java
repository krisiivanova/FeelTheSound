package com.feelthesound.model;

import java.util.Set;

public interface IPlaylist {

	void addSong(Song song);

	Set<Song> getSongs();

	int getId();

	String getName();

	int getUserId();

}
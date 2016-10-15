package com.feelthesound.model;

import java.util.Set;

public interface IPlaylist {

	void addSong(ISong song);

	Set<ISong> getSongs();

	int getId();

	String getName();

	int getUserId();
}
package com.feelthesound.model.DAOs;

import java.util.Set;

import com.feelthesound.model.Like;

public interface ILikeDAO {

	Set<Like> getAllSongsLikes();

	void likeSong(int songId, int userId);

	void dislikeSong(int songId, int userId);

}
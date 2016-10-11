package com.feelthesound.model.DAOs;

import java.util.Set;

import com.feelthesound.model.Like;
import com.feelthesound.model.exceptions.LikeException;

public interface ILikeDAO {

	Set<Like> getAllSongsLikes() throws LikeException;

	void likeSong(int songId, int userId) throws LikeException;

	void dislikeSong(int songId, int userId) throws LikeException;

}
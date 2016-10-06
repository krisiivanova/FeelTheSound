package com.feelthesound.model.DAOs;

import com.feelthesound.model.IUser;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.UserException;

public interface IUserDAO {

	int registerUser(IUser user) throws UserException, ConnectionException;

	int loginUser(IUser user) throws UserException, ConnectionException;

	boolean deleteUser(int userId) throws UserException, ConnectionException;

	boolean isUserExisting(IUser user) throws UserException, ConnectionException;

	void follow(int followerId, int followingId) throws ConnectionException, UserException;

	int getUserSongsCount(int userId) throws ConnectionException;

	int getUserFollowersCount(IUser user) throws ConnectionException, UserException;

	int getUserFollowingCount(IUser user) throws ConnectionException;

}
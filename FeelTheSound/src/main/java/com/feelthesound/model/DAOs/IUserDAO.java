package com.feelthesound.model.DAOs;

import java.util.List;
import java.util.Set;

import com.feelthesound.model.IUser;
import com.feelthesound.model.Song;
import com.feelthesound.model.User;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.UserException;

public interface IUserDAO {
	
	public Set<User> getAllUsers();

	int registerUser(IUser user);

	int loginUser(IUser user);

	boolean isUserExisting(IUser user);

	void follow(int followerId, int followingId);

	int getUserSongsCount(int userId);

	int getUserFollowersCount(IUser user);

	int getUserFollowingCount(IUser user);

	String getProfilePhoto(User user);

	int insertProfilePic(String path, User user);

	List<Song> getAllSongsByUser(int userId);
}
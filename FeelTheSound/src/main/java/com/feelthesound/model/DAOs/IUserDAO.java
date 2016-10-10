package com.feelthesound.model.DAOs;

import java.util.Set;

import com.feelthesound.model.User;

public interface IUserDAO {

	Set<User> getAllUsers();

	int registerUser(String username, String password, String email);

	int loginUser(String username, String password);

	boolean isUserExisting(String username, String password);

	int editFirstName(String username, String firstName);

	int editLastName(String username, String lastName);

	int editCity(String username, String city);

	int editCountry(String username, String country);

	int addFollowing(int followerId, int followedId);

	int getUserFollowersCount(User user);

	int getUserFollowingCount(User user);

	int insertProfilePic(String photoPath, String username);

	String getProfilePhoto(String username);
	
	boolean hasThisEmail(String email);
	
	boolean hasThisUsername(String username);
	
	void saveUser(String username, String password, String email);
	
	User getUserByUsername(String username);
	
	boolean hasUsername(String username);
}
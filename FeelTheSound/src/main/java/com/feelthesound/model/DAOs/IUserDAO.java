package com.feelthesound.model.DAOs;

import java.util.Set;

import com.feelthesound.model.IUser;
import com.feelthesound.model.User;
import com.feelthesound.model.exceptions.UserException;

public interface IUserDAO {

	Set<User> getAllUsers() throws UserException;

	int registerUser(String username, String password, String email) throws UserException;

	int loginUser(String username, String password) throws UserException;

	boolean isUserExisting(String username, String password) throws UserException;

	int editFirstName(String username, String firstName) throws UserException;

	int editLastName(String username, String lastName) throws UserException;

	int editCity(String username, String city) throws UserException;

	int editCountry(String username, String country) throws UserException;

	int insertProfilePic(String photoPath, User user) throws UserException;

	String getProfilePhoto(User user) throws UserException;
	
	boolean hasThisEmail(String email);
	
	boolean hasThisUsername(String username);
	
	void saveUser(String username, String password, String email) throws UserException;
	
	User getUserByUsername(String username);
	
	boolean hasUsername(String username);

	int getUserById(IUser user) throws UserException;

	boolean validLogin(String username, String password);
}
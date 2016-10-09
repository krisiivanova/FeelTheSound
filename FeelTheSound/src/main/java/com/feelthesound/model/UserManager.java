package com.feelthesound.model;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.feelthesound.model.IUser;
import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.UserDAO;

public class UserManager {
	private Map<String, IUser> registerredUsersWithUsername; // username -> user
	private Map<String, IUser> registerredUsersWithEmail; // email -> user
	private static UserManager instance;

	private UserManager() {
		registerredUsersWithUsername = new ConcurrentHashMap<>();
		for (User u : UserDAO.getInstance().getAllUsers()) {
			registerredUsersWithUsername.put(u.getUsername(), u);
			registerredUsersWithEmail.put(u.getEmail(), u);
		}
	}

	public static UserManager getInstance() {
		if (UserManager.instance == null) {
			synchronized (UserManager.class) {
				if (UserManager.instance == null) {
					UserManager.instance = new UserManager();
				}
			}
		}
		return UserManager.instance;
	}
	
	public ArrayList<IUser> allUsers() {
		ArrayList<IUser> users = new ArrayList<IUser>();
		for (IUser u : registerredUsersWithUsername.values()) {
			users.add(u);
		}
		return users;
	}

	public IUser getUserByUsername(String username) {
		System.out.println("user with username: " + username);
		return registerredUsersWithUsername.get(username);
	}

	public boolean isValidLogin(String username, String password) {
		if (!registerredUsersWithUsername.containsKey(username)) {
			return false;
		}

		return registerredUsersWithUsername.get(username).getPassword().equals(password);
	}

	// checking if email exists
	public boolean isValidEmail(String email) {
		return registerredUsersWithEmail.containsKey(email);
	}

	// cheking if username exists
	public boolean validationUsername(String username) {
		return registerredUsersWithEmail.containsKey(username);
	}

	public void regUser(String username, String password, String email) {
		IUser user = new User(0, username, password, email);
	    UserDAO.getInstance().registerUser(user);
		registerredUsersWithEmail.put(username, user);
		registerredUsersWithEmail.put(email, user);
	}
}

package com.feelthesound.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.feelthesound.model.exceptions.UserException;
import com.feelthesound.model.validators.*;

public class User implements IUser {
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String country;
	private String city;
	private String profilePhoto;
	private String email;

	// private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
	// private HashSet<User> following = new HashSet<User>();
	// private HashSet<User> followers = new HashSet<User>();
	// private ArrayList<Song> uploadedSongs = new ArrayList<Song>();
	// private HashSet<Song> likedSongs = new HashSet<Song>();

	public User(int id, String username, String password, String email) throws UserException {
		this.id = id;
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}
	
	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws UserException {
		if (new EmailValidator().validate(email)) {
			this.email = email;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws UserException {
		if (new UsernameValidator().validate(username)) {
			this.username = username;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws UserException {
		if (new PasswordValidator().validate(password)) {
			this.password = password;
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setPhoto(String photo) {
		this.profilePhoto = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

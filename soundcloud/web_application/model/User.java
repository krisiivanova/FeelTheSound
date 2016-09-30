package com.soundcloud.model;

import com.soundcloud.model.exceptions.UserException;
import com.soundcloud.validators.EmailValidator;
import com.soundcloud.validators.PasswordValidator;
import com.soundcloud.validators.UsernameValidator;
import com.soundcloud.validators.ValidationString;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws UserException {
		if (new EmailValidator().validate(email)) {
			this.email = email;
		} else {
			throw new UserException("Email address is not valid.");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws UserException {
		if (new UsernameValidator().validate(username)) {
			this.username = username;
		} else {
			throw new UserException(
					"Username is not valid - only lowercase symbols, _ and 0-9 are allowed, must be between 3-15 symbols");
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
		} else {
			throw new UserException(
					"Not valid password -  at least one digit, at least one lower case, at least one upper case, no"
							+ " spaces, between 6-13 symbols");
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

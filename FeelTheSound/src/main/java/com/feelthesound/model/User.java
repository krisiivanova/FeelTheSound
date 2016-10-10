package com.feelthesound.model;

import com.feelthesound.model.validators.EmailValidator;
import com.feelthesound.model.validators.PasswordValidator;
import com.feelthesound.model.validators.UsernameValidator;

public class User {
	private int id;
	private String password;
	private String email;
	private String username;
	private String firstName;
	private String lastName;
	private String city;
	private String country;
	private String photoPath;

	public User(int id, String email, String password, String username) {
		this.setId(id);
		this.setEmail(email);
		this.setPassword(password);
		this.setUsername(username);
	}

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (new EmailValidator().validate(email)) {
			this.email = email;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
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

	public void setPassword(String password) {
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
		return photoPath;
	}

	public void setPhoto(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", country=" + country + ", city=" + city + ", profilePhoto="
				+ profilePhoto + ", email=" + email + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 0) {
			this.id = id;
		}
	}
}

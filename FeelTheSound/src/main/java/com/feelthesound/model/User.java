package com.feelthesound.model;

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

	public User(int id, String username, String password, String email){
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

	public void setEmail(String email){
		if (new EmailValidator().validate(email)) {
			this.email = email;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username){
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
		return profilePhoto;
	}

	public void setPhoto(String photo) {
		this.profilePhoto = photo;
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
		this.id = id;
	}
}

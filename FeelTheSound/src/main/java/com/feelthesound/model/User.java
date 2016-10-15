package com.feelthesound.model;

import com.feelthesound.model.validators.EmailValidator;
import com.feelthesound.model.validators.PasswordValidator;
import com.feelthesound.model.validators.UsernameValidator;
import com.feelthesound.model.validators.ValidationString;

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

	public User() {

	}

	public User(int id, String username, String password, String email){
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email){
		if (new EmailValidator().validate(email)) {
			this.email = email;
		}
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (new UsernameValidator().validate(username)) {
			this.username = username;
		}
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName){
		if (ValidationString.isValidString(firstName)) {
			this.firstName = firstName;
		}
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName){
		if (ValidationString.isValidString(lastName)) {
			this.lastName = lastName;
		}
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password){
		if (new PasswordValidator().validate(password)) {
			this.password = password;
		}
	}

	@Override
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String getCity() {
		return city;
	}

	public void setCity(String city){
		if (ValidationString.isValidString(city)) {
			this.city = city;
		}
	}

	@Override
	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setPhoto(String profilePhoto){
		if (ValidationString.isValidString(profilePhoto)) {
			this.profilePhoto = profilePhoto;
		}
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 0) {
			this.id = id;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", country=" + country + ", city=" + city + ", profilePhoto="
				+ profilePhoto + ", email=" + email + "]";
	}
}

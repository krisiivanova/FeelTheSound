package com.feelthesound.model;

import com.feelthesound.model.validators.EmailValidator;
import com.feelthesound.model.validators.PasswordValidator;
import com.feelthesound.model.validators.UsernameValidator;

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

	public User(int id, String username, String password, String email) {
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}

	public User() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.IUser#getEmail()
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.IUser#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.IUser#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.IUser#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (new PasswordValidator().validate(password)) {
			this.password = password;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.IUser#getCountry()
	 */
	@Override
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.IUser#getCity()
	 */
	@Override
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.IUser#getProfilePhoto()
	 */
	@Override
	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setPhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.IUser#getId()
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}

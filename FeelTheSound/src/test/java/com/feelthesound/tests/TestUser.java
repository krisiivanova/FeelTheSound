package com.feelthesound.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.FeelTheSoundConfiguration;
import com.feelthesound.model.DAOs.UserDAO;
import com.feelthesound.model.exceptions.UserException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FeelTheSoundConfiguration.class)
public class TestUser {
	
	
	@Autowired
	UserDAO userDao;
	
	@Test
	public void testUser() throws UserException{
		userDao.registerUser("mimi", "123Abcabc", "mimi@abv.bg");
		
		userDao.editFirstName("mimi", "Mariq");
		userDao.editLastName("mimi", "Ivanova");
		
		User user = userDao.getUserByUsername("mimi");
		
		String profilePhoto = userDao.getProfilePhoto(user);
		System.out.println(profilePhoto);
		
		userDao.loginUser("mimi", "123Abcabc");
	}
}

//package com.feelthesound.tests;
//
//import static org.junit.Assert.*;
//
//import java.util.Random;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.feelthesound.model.FeelTheSoundConfiguration;
//import com.feelthesound.model.User;
//import com.feelthesound.model.DAOs.IUserDAO;
//import com.feelthesound.model.DAOs.UserDAO;
//import com.feelthesound.model.exceptions.ConnectionException;
//import com.feelthesound.model.exceptions.UserException;
//
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = FeelTheSoundConfiguration.class)
//public class TestUser {
//	private IUserDAO userDao = new UserDAO();
//	
//	@Test
//	public void testUser() throws UserException, ConnectionException {
//		int num = new Random().nextInt(5000);
//
//		//testing register user
//		int id = userDao.registerUser(new User(0, "gosho-123" + num, "123abcABC", num + "gosho123@abv.bg"));
//		assertNotEquals(id, 0);
//		
//		
//		//testing login user
//		int userid = userDao.loginUser(new User(0, "gosho-123" + num, "123abcABC", num +"gosho123@abv.bg"));
//		assertEquals(id, userid);
//		
//		// //testing delete user
//		// boolean result = userDao.deleteUser(userid);
//		// assertTrue(result);
//
//		// testing if user exists
//		// boolean isExisting = userDao.isUserExisting(new User(0, "gosho-123" +
//		// num, "123abcABC", "gosho123@abv.bg"));
//		// assertTrue(isExisting);
//		
//		//testing follow method
//		User user1 = new User(0, "mimi-123" + num, "456abcAbc", num +"mimi1234@abv.bg");
//		int user1Id   = userDao.registerUser(user1);
//		userDao.follow(id, user1Id);
//		
//		//testing user songs count
//		userDao.getUserSongsCount(user1Id);
//		
//		//testing user followers count 
//		userDao.getUserFollowersCount(user1);
//		
//		//testing user following count
//		userDao.getUserFollowingCount(user1);			
//	}
//}

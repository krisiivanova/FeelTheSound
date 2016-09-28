package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import model.User;
import model.UserDAO;
import model.UserException;

public class TestUser {
	private UserDAO userDao = new UserDAO();
	
	@Test
	public void testUser() throws UserException {
		int num = new Random().nextInt(5000);
		
		int id = userDao.registerUser(new User(0, "Gosho" + num, "123abc"));
		
		assertNotEquals(id, 0);
		
		int userid = userDao.loginUser(new User(0, "Gosho" + num, "123abc"));
		
		assertEquals(id, userid);
		
		boolean result = userDao.deleteUser(userid);
		
		assertTrue(result);
		
//		boolean isExisting = UserDAO.isUserExisting(new User(0, "Gosho" + num, "123abc"));
//		
//		assertTrue(isExisting);
		
		
	}
}

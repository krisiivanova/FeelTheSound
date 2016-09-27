package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

import model.User;
import model.UserDAO;
import model.UserException;

public class UserTest {
    UserDAO userDao = new UserDAO();
   
	
	@Test
	public void testRegisterUser() throws UserException {
		
		int id = userDao.registerUser(new User(0, "Gosho77", "Mosho"));
		
		assertNotEquals(id, 0);
		
		int userid = userDao.loginUser(new User(0, "Gosho10", "Mosho"));
		
		assertEquals(id, userid);
		
	}
	
	
	
	

}

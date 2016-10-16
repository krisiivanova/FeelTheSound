package com.feelthesound.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.feelthesound.model.IUser;
import com.feelthesound.model.User;
import com.feelthesound.model.exceptions.UserException;

@Component
public class UserDAO implements IUserDAO {

	private static final String SELECT_ALL_USERS = "SELECT id, username, password, email FROM feelthesound.users";
	private static final String INSERT_USER = "INSERT INTO feelthesound.users (username, password, email) VALUES (?,md5(?),?)";
	private static final String SELECT_USER = "SELECT id FROM feelthesound.users WHERE email = ? AND password = md5(?)";
	private static final String CHECK_USER_EXISTING = "SELECT COUNT(*) FROM feelthesound.users WHERE username = ? AND password = md5(?)";
	private static final String EDIT_FIRSTNAME = "UPDATE feelthesound.users SET first_name = ? WHERE username = ?";
	private static final String EDIT_LASTNAME = "UPDATE feelthesound.users SET last_name = ? WHERE username = ?";
	private static final String EDIT_CITY = "UPDATE feelthesound.users SET city = ? WHERE username = ?";
	private static final String EDIT_COUNTRY = "UPDATE feelthesound.users SET country = ? WHERE username = ?";
	private static final String SELECT_USER_PROFILE_PHOTO = "SELECT profile_photo FROM feelthesound.users WHERE username = ? ";
	private static final String UPDATE_USER_PHOTO = "UPDATE feelthesound.users SET profile_photo = ? WHERE username = ?";
	private static final String SELECT_USER_BY_ID = "SELECT id from feelthesound.users where username = ?";

	private static volatile IUserDAO userDAO;
	Connection connection = DBConnection.getInstance().getConnection();

	private Map<String, User> usersUsernames; // user name -> user
	private Map<String, User> usersEmails; // email -> user

	private UserDAO() throws UserException {
		usersUsernames = new ConcurrentHashMap<>();
		usersEmails = new ConcurrentHashMap<>();

		for (User user : this.getAllUsers()) {
			usersUsernames.put(user.getUsername(), user);
			usersEmails.put(user.getEmail(), user);
		}
	}

	public static IUserDAO getInstance() throws UserException {
		if (userDAO == null) {
			synchronized (UserDAO.class) {
				if (userDAO == null) {
					userDAO = new UserDAO();
				}
			}
		}
		return userDAO;
	}

	/**
	 * the method returns a set of all the registered users from the database
	 * 
	 * @throws UserException
	 */
	@Override
	public Set<User> getAllUsers() throws UserException {
		Set<User> users = new HashSet<User>();
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(SELECT_ALL_USERS);
			while (resultSet.next()) {
				users.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("email")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Error with getting the users from the database!");
		}

		return users;
	}

	/**
	 * the method saves a user into the database by given user name, password
	 * and email
	 * 
	 * @throws UserException
	 */
	@Override
	public int registerUser(String username, String password, String email) throws UserException {
		PreparedStatement ps = null;
		int userId = 0;
		try {
			ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);

			userId = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Couldn't register the user!");
		}

		return userId;
	}

	/**
	 * the method checks if user with this user name and password exists in
	 * database in order to log in
	 * 
	 * @throws UserException
	 */
	@Override
	public int loginUser(String username, String password) throws UserException {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = connection.prepareStatement(SELECT_USER);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			result = rs.getInt(1);

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Couldn't login the user!");
		}
	}

	/**
	 * the method checks if user with this user name and password exists in
	 * database
	 * 
	 * @throws UserException
	 */
	@Override
	public boolean isUserExisting(String username, String password) throws UserException {
		PreparedStatement statement = null;
		boolean result = false;
		try {
			statement = connection.prepareStatement(CHECK_USER_EXISTING);
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			if (resultSet.getInt(1) == 1) {
				result = true;
			}

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Couldn't find if the users is existing or not!");
		}
	}

	/**
	 * the method edits the first name of a user already saved in the database
	 * 
	 * @throws UserException
	 */
	@Override
	public int editFirstName(String username, String firstName) throws UserException {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = connection.prepareStatement(EDIT_FIRSTNAME);
			ps.setString(1, firstName);
			ps.setString(2, username);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Couldn't edit the firstname of " + username);
		}

		return result;
	}

	/**
	 * the method edits the last name of a user already saved in the database
	 * 
	 * @throws UserException
	 */
	@Override
	public int editLastName(String username, String lastName) throws UserException {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = connection.prepareStatement(EDIT_LASTNAME);
			ps.setString(1, lastName);
			ps.setString(2, username);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Couldn't edit the lastname of !" + username);
		}

		return result;
	}

	/**
	 * the method edits the city of a user already saved in the database
	 * 
	 * @throws UserException
	 */
	@Override
	public int editCity(String username, String city) throws UserException {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = connection.prepareStatement(EDIT_CITY);
			ps.setString(1, city);
			ps.setString(2, username);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Couldn't edit the city of !" + username);
		}

		return result;
	}

	/**
	 * the method edits the country of a user already saved in the database
	 * 
	 * @throws UserException
	 */
	@Override
	public int editCountry(String username, String country) throws UserException {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = connection.prepareStatement(EDIT_COUNTRY);
			ps.setString(1, country);
			ps.setString(2, username);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Coundn't edit the country of !" + username);
		}

		return result;
	}

	/**
	 * the method inserts into the users table in the database a profile photo
	 * path different from the default one
	 * 
	 * @throws UserException
	 */
	@Override
	public int insertProfilePic(String path, User user) throws UserException {
		int result = 0;
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE_USER_PHOTO, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, path);
			statement.setString(2, user.getUsername());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) {
				result = rs.getInt(1);
				return result;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Couldn't insert the profile photo!");
		}

		return result;
	}

	/**
	 * the method gets the profile photo path of a user
	 * 
	 * @throws UserException
	 */
	@Override
	public String getProfilePhoto(User user) throws UserException {
		PreparedStatement statement = null;
		String result = null;
		try {
			statement = connection.prepareStatement(SELECT_USER_PROFILE_PHOTO);
			statement.setString(1, user.getUsername());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
				return result;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Couldn't get the profile photo!");
		}

		return result;
	}

	/**
	 * the method gets the user id from the users table in the database by a
	 * given user
	 * 
	 * @throws UserException
	 */
	@Override
	public int getUserById(IUser user) throws UserException {
		PreparedStatement statement = null;
		int userId = 0;
		try {
			statement = connection.prepareStatement(SELECT_USER_BY_ID);
			statement.setString(1, user.getUsername());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				userId = rs.getInt(1);
				return userId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Couldn't get the user id!");
		}
		return userId;
	}

	/**
	 * the method checks if there is already user with this email in the
	 * collection usersEmails
	 */
	@Override
	public boolean hasThisEmail(String email) {
		return usersEmails.containsKey(email);
	}

	/**
	 * the method checks if there is already user with this user name in the
	 * collection usersUsernames
	 */
	@Override
	public boolean hasThisUsername(String username) {
		return usersUsernames.containsKey(username);
	}

	/**
	 * the method checks if the user name and password of a user are valid for
	 * them to log in
	 */
	@Override
	public boolean validLogin(String username, String password) {
		if (!usersUsernames.containsKey(username)) {
			return false;
		}
		return usersUsernames.get(username).getPassword().equals(password);
	}

	/**
	 * the method saves a user into the database by calling the registerUser
	 * method
	 */
	@Override
	public void saveUser(String username, String password, String email) throws UserException {
		int userId = this.registerUser(username, password, email);
		User user = new User(userId, username, password, email);

		usersUsernames.put(username, user);
		usersEmails.put(email, user);
	}

	/**
	 * the method gets a user by their user name
	 */
	@Override
	public User getUserByUsername(String username) {
		return usersUsernames.get(username);
	}

	/**
	 * the method checks if a user with this user name already exists
	 */
	@Override
	public boolean hasUsername(String username) {
		return usersUsernames.containsKey(username);
	}
}

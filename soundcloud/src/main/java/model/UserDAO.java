package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	private static final String INSERT_USER_SQL = "INSERT INTO soundcloud.users(user_id, username, password) VALUES (null, ?, md5(?) )";
	private static final String SELECT_USER_SQL = "SELECT user_id FROM Users WHERE username = ? AND password = md5(?)";
	private static final String DELETE_USER_SQL = "DELETE FROM soundcloud.users WHERE user_id = ?";
	private static final String CHECK_USER_EXISTING = "SELECT COUNT(*) FROM soundcloud.users WHERE username = ? AND password = md5(?)";
	private static final String CHECK_IF_LIKED = "SELECT count(id) from soundcloud.likes where user_id= ? and songs_id = ? ;";
	private static final String ADD_LIKE_TO_SONG = "INSERT into soundcloud.likes (songs_id, user_id) values (?,?)";
	// private static final String ADD_DISLIKE_TO_SONG = "DELETE FROM
	// soundcloud.likes songs_id = ? , user_id = ?";
	private static final String FOLLOW = "INSERT into soundcloud.follows (follower_id,following_id) values (?,?)";
	private static final String UNFOLLOW = "DELETE from soundcloud.follows where follower_id = ? following_id = ?";
	private static final String INSERT_PROFILE_PHOTO = "INSERT into soundcloud.users(photo) values (?) WHERE user_id = (?)";
	
	public int registerUser(User user) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new UserException("User registration failed!");
		}
	}

	public int loginUser(User user) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_USER_SQL);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				throw new UserException("User login failed!");
			}

			return rs.getInt(1);
		} catch (Exception e) {
			throw new UserException("Something went wrong!");
		}
	}

	public boolean deleteUser(int userId) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(DELETE_USER_SQL);
			ps.setInt(1, userId);
			int deletedRows = ps.executeUpdate();
			return deletedRows >= 1;
		} catch (Exception e) {
			throw new UserException("Something went wrong!");
		}
	}

	public boolean isUserExisting(User user) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();

		boolean result = false;
		try {
			PreparedStatement statement = connection.prepareStatement(CHECK_USER_EXISTING);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			if (resultSet.getInt(1) == 1) {
				result = true;
			}

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("Failed check user existing. Try again.");
		}
	}

	public void like(int userId, int songId) {
		Connection connection = DBConnection.getInstance().getConnection();
		try {

			PreparedStatement statement = connection.prepareStatement(CHECK_IF_LIKED);
			statement.setInt(1, userId);
			statement.setInt(2, songId);
			ResultSet rs = statement.executeQuery();
			rs.next();
			int isLiked = rs.getInt(1);

			PreparedStatement statement1 = null;
			if (isLiked >= 1)
				return;

			statement1 = connection.prepareStatement(ADD_LIKE_TO_SONG);
			statement1.setInt(1, songId);
			statement1.setInt(2, userId);
			statement1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void follow(int followerId, int followingId) {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(FOLLOW);
			statement.setInt(1, followerId);
			statement.setInt(2, followingId);
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void unFollow(int followerId, int followingId) {
//		Connection connection = DBConnection.getInstance().getConnection();
//		try {
//			PreparedStatement statement = connection.prepareStatement(UNFOLLOW);
//			statement.setInt(1, followerId);
//			statement.setInt(2, followingId);
//			statement.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void insertProfilePhoto(int userId, String path) {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT_PROFILE_PHOTO);
			statement.setString(1, path);;
			statement.setInt(2,  userId);
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}

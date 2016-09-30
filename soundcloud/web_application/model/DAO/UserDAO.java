package com.soundcloud.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.soundcloud.model.IUser;
import com.soundcloud.model.Song;
import com.soundcloud.model.exceptions.ConnectionException;
import com.soundcloud.model.exceptions.UserException;

public class UserDAO {
    
	private static final String SELECT_ALL_SONGS_BY_USER = "SELECT count(*) FROM soundcloud.songs WHERE uploader_id = ?";
	private static final String INSERT_USER_SQL = "INSERT INTO soundcloud.users(id, username, password, email) VALUES (null, ?, md5(?), ?)";
	private static final String SELECT_USER_SQL= "SELECT user_id FROM soundcloud.users WHERE email = ? AND password = md5(?)";
	private static final String DELETE_USER_SQL = "DELETE FROM soundcloud.users WHERE id = ?";
	private static final String CHECK_USER_EXISTING_SQL = "SELECT COUNT(*) FROM soundcloud.users WHERE email = ? AND password = md5(?)";
    private static final String FOLLOW_USER_SQL = "INSERT into soundcloud.follows (follower_id , following_id) values (?,?)";
    private static final String SELECT_ALL_SONGS_SQL = "SELECT s.id, s.song_name, s.artist_name, s.genre FROM songs s " + "WHERE s.user_id = ? " + "ORDER BY postadate; ";
	private static final String SELECT_USER_FOLLOWERS_COUNT = "SELECT COUNT(follower_id) FROM soundcloud.follows WHERE following_id = ?";
	private static final String SELECT_USER_FOLLOWING_COUNT = "SELECT COUNT(following_id) FROM soundcloud.follows WHERE follower_id = ?";
	
	public int registerUser(IUser user) throws UserException, ConnectionException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("User registration failed!");
		}
	}
	

	public int loginUser(IUser user) throws UserException, ConnectionException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_USER_SQL);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() == false) {
				throw new UserException("User login failed!");
			}

			return rs.getInt(1);
			
		} catch (SQLException e ) {
			e.printStackTrace();
			throw new UserException("Something went wrong!");
		}
}
	

	public boolean deleteUser(int userId) throws UserException, ConnectionException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(DELETE_USER_SQL);
			ps.setInt(1, userId);
			int deletedRows = ps.executeUpdate();
			
			return deletedRows >= 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("Not able to delete this user!");
		}
	}
	

	public boolean isUserExisting(IUser user) throws UserException, ConnectionException {
		Connection connection = DBConnection.getInstance().getConnection();

		boolean result = false;
		try {
			PreparedStatement statement = connection.prepareStatement(CHECK_USER_EXISTING_SQL);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			if (resultSet.getInt(1) == 1) {
				result = true;
			}

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("Failed checking if user exists!");
		}
	}
	
	public void follow(int followerId, int followingId) throws ConnectionException, UserException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(FOLLOW_USER_SQL);
			statement.setInt(1, followerId);
			statement.setInt(2, followingId);
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("Cannot follow this user!");
		}
	}
	
	public static List<Song> getAllSongsByUser(int userId) throws ConnectionException {
		Connection connection = DBConnection.getInstance().getConnection();
		List<Song> result = new ArrayList<Song>();

		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SONGS_SQL);
			statement.setInt(1, userId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				result.add(new Song(0, rs.getString(1), rs.getString(2), rs.getString(3), userId));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public  int getUserSongsCount(int userId) throws ConnectionException {
		Connection connection = DBConnection.getInstance().getConnection();
		int result = 0;
		try {
		    PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SONGS_BY_USER);
		    statement.setInt(1, userId);

			ResultSet rs = statement.executeQuery();
			rs.next();	
			
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public  int getUserFollowersCount(int userId) throws ConnectionException {
		Connection connection = DBConnection.getInstance().getConnection();
		int result = 0;
		try {
			 PreparedStatement statement = connection.prepareStatement(SELECT_USER_FOLLOWERS_COUNT);
			 statement.setInt(1, userId);

			ResultSet rs = statement.executeQuery();
			rs.next();	
			result = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public  int getUserFollowingCount (int userId) throws ConnectionException {
		Connection connection = DBConnection.getInstance().getConnection();
		int result = 0;
		try {
			 PreparedStatement statement = connection.prepareStatement(SELECT_USER_FOLLOWING_COUNT);
			 statement.setInt(1, userId);

			ResultSet rs = statement.executeQuery();
			rs.next();	
			result = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

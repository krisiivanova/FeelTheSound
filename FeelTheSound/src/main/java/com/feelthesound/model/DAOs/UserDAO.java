package com.feelthesound.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feelthesound.model.IUser;
import com.feelthesound.model.Song;
import com.feelthesound.model.User;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.UserException;

@Component
public class UserDAO implements IUserDAO {

	private static volatile UserDAO instance;
	Connection connection = DBConnection.getInstance().getConnection();

	private static final String SELECT_ALL_USERS = "SELECT id, username, password, email FROM feelthesound.users";
	private static final String SELECT_ALL_SONGS_BY_USER = "SELECT count(*) FROM feelthesound.songs WHERE uploader_id = ?";
	private static final String INSERT_USER_SQL = "INSERT INTO feelthesound.users(id, username, password, email) VALUES (null, ?, md5(?), ?)";
	private static final String SELECT_USER_SQL = "SELECT user_id FROM feelthesound.users WHERE email = ? AND password = md5(?)";
	private static final String CHECK_USER_EXISTING_SQL = "SELECT COUNT(*) FROM feelthesound.users WHERE username = ? AND password = md5(?)";
	private static final String FOLLOW_USER_SQL = "INSERT into feelthesound.follows (follower_id , following_id) values (?,?)";
	private static final String SELECT_ALL_SONGS_SQL = "SELECT id, song_name, artist_name, genre FROM songs"
			+ "WHERE user_id = ? " + "ORDER BY postadate; ";
	private static final String SELECT_USER_FOLLOWERS_COUNT = "SELECT COUNT(follower_id) FROM feelthesound.follows WHERE following_id = ?";
	private static final String SELECT_USER_FOLLOWING_COUNT = "SELECT COUNT(following_id) FROM feelthesound.follows WHERE follower_id = ?";
	private static final String SELECT_USER_PROFILE_PHOTO = "SELECT photo FROM feelthesound.users WHERE username = ? ";
	private static final String UPDATE_USER_PHOTO = "UPDATE feelthesound.users SET photo = ? WHERE username = ?";

	public static UserDAO getInstance() {
		if (instance == null) {
			synchronized (UserDAO.class) {
				if (instance == null) {
					instance = new UserDAO();
				}
			}
		}
		return instance;
	}

	@Override
	public Set<User> getAllUsers() {
		Set<User> users = new HashSet<User>();
		try {
			Statement st = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery(SELECT_ALL_USERS);
			while (resultSet.next()) {
				users.add(new User(resultSet.getInt("id"), resultSet.getString("email"),
						resultSet.getString("password"), resultSet.getString("username")));
			}
		} catch (SQLException e) {
			System.out.println("Oops, cannot make statement.");
			return users;
		}

		return users;
	}

	@Override
	public int registerUser(IUser user) {
		PreparedStatement ps = null;
		int userId = 0;
		try {
			ps = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());

			userId = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error with register user");
			e.printStackTrace();
		}

		return userId;
	}

	@Override
	public int loginUser(IUser user) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = connection.prepareStatement(SELECT_USER_SQL);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			result = rs.getInt(1);
			return result;

		} catch (SQLException e) {
			System.out.println("Error with login user");
			e.printStackTrace();
		}

		return result;
	}

	public boolean isUserExisting(IUser user) {
		PreparedStatement statement = null;
		boolean result = false;
		try {
			statement = connection.prepareStatement(CHECK_USER_EXISTING_SQL);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			if (resultSet.getInt(1) == 1) {
				result = true;
			}

			return result;

		} catch (SQLException e) {
			System.out.println("Error with checking if user exists ");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void follow(int followerId, int followingId) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(FOLLOW_USER_SQL);
			statement.setInt(1, followerId);
			statement.setInt(2, followingId);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error with checking if user exists ");
			e.printStackTrace();
		}
	}

	@Override
	public List<Song> getAllSongsByUser(int userId) {
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

	@Override
	public int getUserSongsCount(int userId) {
		int result = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SONGS_BY_USER);
			statement.setInt(1, userId);

			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getInt(1);
			return result;
		} catch (SQLException e) {
			System.out.println("Error with getting the users songs count");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int getUserFollowersCount(IUser user) {
		int result = 0;
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_USER_FOLLOWERS_COUNT);
			statement.setInt(1, user.getId());

			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getInt(1);

			return result;
		} catch (SQLException e) {
			System.out.println("Error with getting the users followers count");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int getUserFollowingCount(IUser user) {
		int result = 0;
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_USER_FOLLOWING_COUNT);
			statement.setInt(1, user.getId());

			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getInt(1);

		} catch (SQLException e) {
			System.out.println("Error with getting the users following count");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int insertProfilePic(String path, User user) {
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
			System.out.println("Error with getting the users inserting profile photo");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String getProfilePhoto(User user) {
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
			System.out.println("Error with getting the getting the profile photo");
			e.printStackTrace();
		}
		return result;
	}

}

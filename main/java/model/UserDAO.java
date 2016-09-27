package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

	private static final String INSERT_USER_SQL = "INSERT INTO soundcloud.users(user_id, username, password) VALUES (null, ?, md5(?) )";
	private static final String SELECT_USER_SQL = "SELECT user_id FROM Users WHERE username = ? AND password = md5(?)";

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
			e.printStackTrace();
			throw new UserException("Registration failed");
		}
	}

	public int loginUser(User user) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_USER_SQL);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			rs.next();
//			if (rs.next() == false) {
//				throw new UserException("User login failed");
//			}

			return rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("User registration failed!");
		}
	}
}

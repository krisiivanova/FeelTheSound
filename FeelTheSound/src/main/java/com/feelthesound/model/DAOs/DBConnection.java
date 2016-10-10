package com.feelthesound.model.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection instance;
	private Connection connection;
	private static final String DB_SCHEMA = "feelthesound";
	private static final String DB_PORT = "3306";
	private static final String DB_HOST = "localhost";
	private static final String DB_PASSWORD = "123456789bbb";
	private static final String DB_USERNAME = "Bozhidar";

	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_SCHEMA,
					DB_USERNAME, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load database driver: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Unable to connect to database: " + e.getMessage());
		}
	}

	public static DBConnection getInstance() {
		if (DBConnection.instance == null) {
			synchronized (DBConnection.class) {
				if (DBConnection.instance == null) {

					DBConnection.instance = new DBConnection();
				}
			}
		}

		return DBConnection.instance;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

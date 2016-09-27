package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static DBConnection instance;
	private Connection connection;
	private static final String DB_SCHEMA = "soundcloud";
	private static final String DB_PORT = "3306";
	private static final String DB_HOST = "localhost";
	private static final String DB_PASSWORD = "jej611";
	private static final String DB_USERNAME = "root";
	
	private DBConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(
				"jdbc:mysql://" + DB_HOST + ":" + 
				DB_PORT + "/" + 
				DB_SCHEMA, 
				DB_USERNAME, DB_PASSWORD);
	}
	
	
	public static DBConnection getInstance() {
		if (instance == null) {
			try {
				instance = new DBConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}	
}

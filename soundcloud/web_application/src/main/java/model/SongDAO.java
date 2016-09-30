package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SongDAO {
	private static final String INSERT_SONG = "INSERT INTO songs(id, artist_name, song_name, uploader_id) VALUES (null, ?, ?, ?);";
	private static final String DELETE_SONG= "DELETE FROM songs WHERE uploader_id = ?";
	
	public int insertSong(Song song) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_SONG, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, song.getArtist());
			ps.setString(2, song.getName());
			ps.setInt(3,  song.getUserId());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Song insert failed!");
		}
	}
	
	public void deleteSong(Song song) throws UserException{
		Connection connection = DBConnection.getInstance().getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement(DELETE_SONG, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, song.getUserId());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Song deletion failed!");
		}
	}
}

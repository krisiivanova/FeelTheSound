package com.soundcloud.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.soundcloud.model.Playlist;
import com.soundcloud.model.exceptions.ConnectionException;
import com.soundcloud.model.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlaylistDAO {
	private static final String INSERT_INTO_MAPPING = "INSERT INTO playlist_has_songs VALUES (?,?) WHERE playlist_id = ? AND song_id = ? "; 
	private static final String INSERT_PLAYLIST = "INSERT INTO playlists(id, name, user_id) VALUES (null, ?, ?)";
	private static final String SELECT_USER_PLAYLIST = "SELECT id FROM users JOIN playlists ON user_id = ?";
	

	public int addNewPlaylist(Playlist playlist) throws UserException, ConnectionException{
		Connection connection = DBConnection.getInstance().getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement(INSERT_PLAYLIST, 
													Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, playlist.getName());
			ps.setInt(2, playlist.getUserId());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			return rs.getInt(1);
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new UserException("Faild to create playlist!");	
		}
	}

	
	public void addSongIntoPlaylist(Playlist playlist, int songId) throws ConnectionException {
		Connection connection = DBConnection.getInstance().getConnection();
		
		try {
			connection.setAutoCommit(false);
			
    		PreparedStatement ps = connection.prepareStatement(SELECT_USER_PLAYLIST);
    		ps.setInt(1, playlist.getUserId());
		    ps.executeQuery();
		    
		    PreparedStatement ps1 = connection.prepareStatement(INSERT_INTO_MAPPING);
		    ps1.setInt(1, playlist.getId());
		    ps1.setInt(2, songId);
				
			connection.commit();
			
		} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
}

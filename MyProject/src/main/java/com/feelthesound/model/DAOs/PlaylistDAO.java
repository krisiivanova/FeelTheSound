package com.feelthesound.model.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feelthesound.model.Playlist;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Component
public class PlaylistDAO implements IPlaylistDAO {
	
	private static final String INSERT_INTO_MAPPING = "INSERT INTO playlist_has_songs VALUES (?,?) WHERE playlist_id = ? AND song_id = ? "; 
	private static final String INSERT_PLAYLIST = "INSERT INTO playlists(id, name, user_id) VALUES (null, ?, ?)";
	private static final String SELECT_USER_PLAYLIST = "SELECT id FROM users JOIN playlists ON user_id = ?";
	
	/* (non-Javadoc)
	 * @see com.feelthesound.model.DAOs.IPlaylistDAO#addNewPlaylist(com.feelthesound.model.Playlist)
	 */
	@Override
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

	
	/* (non-Javadoc)
	 * @see com.feelthesound.model.DAOs.IPlaylistDAO#addSongIntoPlaylist(com.feelthesound.model.Playlist, int)
	 */
	@Override
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

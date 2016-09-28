package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlaylistDAO {
	private static final String INSERT_SONG = "INSERT INTO songs(id, artist_name, song_name, uploader_id) VALUES (null, ?, ?, ?);";
//	private static final String INSERT_INTO_MAPPING = "INSERT INTO playlist_has_songs VALUES (?,?) WHERE("
//			+ ");
	private static final String SELECT_SONG = "SELECT id FROM songs WHERE id = ? ";
	private static final String INSERT_PLAYLIST = "INSERT INTO playlists(id, name, user_id) VALUES (null, ?, ?)";
	private static final String SELECT_PLAYLIST = "SELECT id FROM playlists WHERE user_id = ?";
	
	public int addNewPlaylist(Playlist playlist) throws UserException{
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

	
//	public void addSongIntoPlaylist(int playlistId, int songId) {
//		Connection connection = DBConnection.getInstance().getConnection();
//		
//		try {
//			connection.setAutoCommit(false);
//			
//    		PreparedStatement ps = connection.prepareStatement(INSERT_INTO_MAPPING);
//				ps.setInt(1, songId);
//				ps.setInt(2, playlistId);
//				ps.executeUpdate();
//				
//			connection.commit();
//		} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
}

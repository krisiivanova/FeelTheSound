//package model;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//public class PlaylistDAO {
//	private static final String INSERT_SONG = "INSERT INTO songs(id, artist_name, song_name) VALUES (null, ?, ?);";
//	private static final String INSERT_INTO_MAPPING = "INSERT INTO playlist_has_songs VALUES (?,?)";
//	private static final String INSERT_PLAYLIST = "INSERT INTO playlists(id, name) VALUES (null, ?)";
//	private static final String SELECT_ALL_QUESTIONS_IN_PLAYLIST= "SELECT p.id as 'playlist_id', p.name as 'playlist_name', s.id as 'song_id', s.song_name as 'song_name', s.artist_name as 'artist_name' FROM playlist p "
//			+ "JOIN playlist_has_songs ps ON "
//			+ "(p.id = ps.playlist_id) "
//			+ "JOIN songs s ON (ps.sond_id = s.id);";
//	
//	public void addSongIntoPlaylist(Playlist playlist) {
//		Connection connection = DBConnection.getInstance().getConnection();
//		
//		try {
//			connection.setAutoCommit(false);
//			
//			PreparedStatement ps = connection.prepareStatement(INSERT_PLAYLIST, 
//													Statement.RETURN_GENERATED_KEYS);
//			ps.setString(1, playlist.getName());
//			
//			ps.executeUpdate();
//			
//			ResultSet rs = ps.getGeneratedKeys();
//			rs.next();
//			int newPlaylistId = rs.getInt(1);
//			
//			for (Song song : playlist.getSongs()) {
//				ps = connection.prepareStatement(INSERT_SONG, 
//						Statement.RETURN_GENERATED_KEYS);
//				ps.setString(1, song.getArtist());
//				ps.setString(2, song.getName());
//				ps.executeUpdate();
//				rs = ps.getGeneratedKeys();
//				rs.next();
//				
//				int newSongId = rs.getInt(1);
//				
//				ps = connection.prepareStatement(INSERT_INTO_MAPPING);
//				ps.setInt(1, newSongId);
//				ps.setInt(2, newPlaylistId);
//				ps.executeUpdate();
//			}
//			
//			connection.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			try {
//				connection.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//	
//	public List<Song> getAllQuestions() {
//		Connection connection = DBConnection.getInstance().getConnection();
//		
//		try {
//			ArrayList<Song> result = new ArrayList<Song>();
//			Statement st = connection.createStatement();
//			ResultSet rs  = st.executeQuery(SELECT_ALL_QUESTIONS_IN_PLAYLIST);
//			
//			int lastPId = 0;
//			Playlist p = null;
//			while (rs.next()) {
//				int pId = rs.getInt(1);
//				String pName = rs.getString(2);
//				int sId = rs.getInt(3);
//				String sName = rs.getString(4);
//				String sArtist = rs.getString(5);
//			
//				if (lastPId != pId) {
//					 p = new Playlist(pId, pName);
//					 result.add(p);
//					 lastPId = pId;
//				}
//				
//				q.addAnswer(new Answer(aId, aText));
//			}
//			
//			return result;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			throw new QuestionException("Cannot load all questions.Sorry!");
//		}
//	}
//	
//}

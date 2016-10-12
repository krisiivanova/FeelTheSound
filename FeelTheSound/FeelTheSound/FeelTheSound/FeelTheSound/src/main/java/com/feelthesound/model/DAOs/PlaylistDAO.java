package com.feelthesound.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.feelthesound.model.exceptions.PlaylistException;

@Component
public class PlaylistDAO implements IPlaylistDAO{
	
	private static final String SELECT_ALL_PLAYLISTS = "SELECT name FROM feelthesound.playlists WHERE user_id = ?";
	private static final String INSERT_PLAYLIST= "INSERT INTO feelthesound.playlists (name, user_id) VALUES (?,?)";
	private static final String INSERT_SONG_IN_PLAYLIST= "INSERT INTO feelthesound.playlist_with_songs (playlist_id,song_id) VALUES (?,?)";
	
	private static volatile IPlaylistDAO playlistDAO;
	Connection connection = DBConnection.getInstance().getConnection();

	public static IPlaylistDAO getInstance() {
		if (playlistDAO == null) {
			synchronized (PlaylistDAO.class) {
				if (playlistDAO == null) {
					playlistDAO = new PlaylistDAO();
				}
			}
		}
		return playlistDAO;
	}
	
	// getting all playlists by user
	/* (non-Javadoc)
	 * @see com.feelthesound.model.DAOs.IPlaylistDAO#getAllPlaylistsNamesByUser(int)
	 */
	@Override
	public Set<String> getAllPlaylistsNamesByUser(int userId) throws PlaylistException {
		HashSet<String> playlistNames = new HashSet<String>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SELECT_ALL_PLAYLISTS);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				playlistNames.add(rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Coundn't get all the playlists of the user!");
		}

		return playlistNames;
	}
	
	//creating new playlist 
	/* (non-Javadoc)
	 * @see com.feelthesound.model.DAOs.IPlaylistDAO#addPlaylist(java.lang.String, int)
	 */
	@Override
	public int addPlaylist(String name, int userId) throws PlaylistException {
		int playlistId = 0;
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(INSERT_PLAYLIST, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ps.setInt(2, userId);
			playlistId = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Coundt add new playlist!");
		}

		return playlistId;
	}
	
	//adding song the a playlist 
	/* (non-Javadoc)
	 * @see com.feelthesound.model.DAOs.IPlaylistDAO#addSongInPlaylist(int, int)
	 */
	@Override
	public int addSongInPlaylist(int playlistId, int songId) throws PlaylistException {
		int result = 0;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(INSERT_SONG_IN_PLAYLIST);
			ps.setInt(1, playlistId);
			ps.setInt(2, songId);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Coundt add song into playlist!");
		}
		
		return result;
	}
}

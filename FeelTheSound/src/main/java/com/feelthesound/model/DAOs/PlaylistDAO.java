package com.feelthesound.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.feelthesound.model.IPlaylist;
import com.feelthesound.model.IUser;
import com.feelthesound.model.Playlist;
import com.feelthesound.model.User;
import com.feelthesound.model.Playlist;
import com.feelthesound.model.exceptions.PlaylistException;
import com.feelthesound.model.exceptions.UserException;

@Component
public class PlaylistDAO implements IPlaylistDAO{
	private static final String SELECT_ALL_PLAYLISTS_OF_USER = "SELECT id, name FROM feelthesound.playlists WHERE user_id = ?";
	private static final String INSERT_PLAYLIST= "INSERT INTO feelthesound.playlists (name, user_id) VALUES (?,?)";
	private static final String INSERT_SONG_IN_PLAYLIST= "INSERT INTO feelthesound.playlist_with_songs (playlist_id,song_id) VALUES (?,?)";
	private static final String SELECT_ALL_SONGS_IN_PLAYLIST = "SELECT song_id FROM feelthesound.playlist_with_songs WHERE playlist_id = ?";
	private static final String SELECT_IF_PLAYLIST_EXISTS =  "SELECT * FROM feelthesound.playlists WHERE user_id=? AND name=?";
//	private static final String DELETE_USER_PLAYLIST =   "DELETE FROM feelthesound.playlist"
			
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

	public IPlaylist addPlaylist(String name, IUser user) throws PlaylistException{
		int playlistId = 0;
		Playlist playlist = null;
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(INSERT_PLAYLIST, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			int userId = UserDAO.getInstance().getUserById(user);
			ps.setInt(2, userId);
			playlistId = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				playlistId = rs.getInt(1);
			}
			
			playlist = new Playlist(name, userId);
			
			System.err.println("Playlist id: " + playlistId);
			
			playlist.setId(playlistId);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Couldn't add new playlist!");
		} catch (UserException e) {
			e.printStackTrace();
		}

		return playlist;
	}
	

	public void addSongInPlaylist(Integer playlistId, Integer songId) throws PlaylistException {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(INSERT_SONG_IN_PLAYLIST);
			ps.setInt(1, playlistId);
			ps.setInt(2, songId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Coundt add song into playlist!");
		}
	}

	public List<Integer> getPlaylistSongs(int playlistId) {
		List<Integer> postsInPlaylist = new ArrayList<Integer>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(SELECT_ALL_SONGS_IN_PLAYLIST);
			ps.setInt(1, playlistId);
			rs = ps.executeQuery();
			while (rs.next()) {
				postsInPlaylist.add(rs.getInt("song_id"));				
			}
		} catch (SQLException e) {
			System.out.println("Error in getPlaylistPosts method in PlaylistDAO");
			e.printStackTrace();
		}
		
		return postsInPlaylist;
	}

	@Override
	public boolean ifPlaylistExist(User user, String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		try{
			ps=connection.prepareStatement(SELECT_IF_PLAYLIST_EXISTS);
			int userId  = UserDAO.getInstance().getUserById(user);
			ps.setInt(1, userId);
			ps.setString(2, name);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UserException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<IPlaylist> getAllPlaylists(Integer userId) {
		List<IPlaylist> playlists = new ArrayList<IPlaylist>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SELECT_ALL_PLAYLISTS_OF_USER, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				playlists.add(new Playlist(rs.getInt("id"), rs.getString("name")));
			}
			
		} catch (SQLException e) {
			System.out.println("Cannot get all Playlists from getAllPlaylists() in PlaylistDAO");
		}
		
		return playlists;
	}
	
	public void deletePlaylist(int userId, int playlistId) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SELECT_ALL_PLAYLISTS_OF_USER, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			System.out.println("Cannot get all Playlists from getAllPlaylists() in PlaylistDAO");
		}
	}
}

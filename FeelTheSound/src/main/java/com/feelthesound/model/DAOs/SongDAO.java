package com.feelthesound.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.feelthesound.model.ISong;
import com.feelthesound.model.Song;
import com.feelthesound.model.exceptions.SongException;

@Component
public class SongDAO implements ISongDAO {
	
	private static final String SELECT_SONG_BY_PLAYLIST = "SELECT * FROM feelthesound.songs s"
			+ " INNER JOIN feelthesound.playlist_with_songs ps on s.id = ps.song_id"
			+ " INNER JOIN feelthesound.playlists p on ps.playlist_id = p.id WHERE p.id = ?";
	private static final String SELECT_BY_SEARCH_TEXT = "SELECT * FROM feelthesound.songs WHERE name LIKE ? OR artist LIKE ? ";
	private static final String SELECT_BY_USER = "SELECT * FROM feelthesound.songs WHERE uploader_id=?";
	private static final String INSERT_SONG = "INSERT INTO feelthesound.songs(id, name, artist, upload_date, genre, uploader_id, song_path) VALUES (null, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_IF_SONG_IS_INSERTED = "SELECT count(*) FROM feelthesound.songs WHERE name = ? AND artist = ?";
	private static final String DELETE_SONG = "DELETE FROM feelthesound.songs WHERE name = ? AND artist = ? AND uploader_id = ?";
	private static final String SELECT_ALL_LIKED = "SELECT * FROM feelthesound.songs JOIN feelthesound.likes ON songs.id = likes.song_id AND likes.user_id = ?";
	private static final String SELECT_LASTLY_ADDED = "SELECT name, artist, song_path FROM feelthesound.songs ORDER BY upload_date DESC LIMIT 1";
	
	private static volatile ISongDAO songDAO;
	Connection connection = DBConnection.getInstance().getConnection();

	public static ISongDAO getInstance() {
		if (songDAO == null) {
			synchronized (SongDAO.class) {
				if (songDAO == null) {
					songDAO = new SongDAO();
				}
			}
		}
		
		return songDAO;
	}
	
	
	// inserting a song into the database
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.DAOs.ISongDAO#insertSong(int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.util.Date)
	 */
	@Override
	public int insertSong(ISong song) throws SongException {
		try {
			PreparedStatement ps1 = connection.prepareStatement(SELECT_IF_SONG_IS_INSERTED,
					Statement.RETURN_GENERATED_KEYS);

			ps1.setString(1, song.getName());
			ps1.setString(2, song.getArtist());

			ResultSet rs = ps1.executeQuery();
			rs.next();
			int isAlreadyInserted = rs.getInt(1);

			if (isAlreadyInserted >= 1) {
				throw new SongException("This song has already been uploaded!");
			}

			PreparedStatement ps = connection.prepareStatement(INSERT_SONG, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, song.getName());
			ps.setString(2, song.getArtist());
			ps.setTimestamp(3, Timestamp.valueOf(song.getUploadDate()));
			ps.setString(4, song.getGenre());
			ps.setInt(5, song.getUploaderId());
			ps.setString(6, song.getSongPath());

			ps.executeUpdate();
			
			ResultSet rs1 = ps.getGeneratedKeys();
			rs1.next();

			return rs1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SongException("Coudnt insert the song into database!");

		}
	}

	@Override
	public void deleteSong(ISong song) throws SongException {
		PreparedStatement ps = null;
		try {
			connection.prepareStatement(DELETE_SONG, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, song.getName());
			ps.setString(2, song.getArtist());
			ps.setInt(3, song.getUploaderId());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SongException("Couldn't delete the song!");
		}
	}

	@Override
	public List<ISong> getSongsByUser(Integer userId) {
		List<ISong> list = new ArrayList<>();
		try {
			PreparedStatement ps1 = connection.prepareStatement(SELECT_BY_USER,Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, userId);
			
			ResultSet resultSet = ps1.executeQuery();
			while (resultSet.next()) {
				ISong song = new Song(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("artist"),
						resultSet.getString("genre"), resultSet.getInt("uploader_id"),
						resultSet.getString("song_path"));
				list.add(song);
			}

		} catch (SQLException e) {
			System.out.println("Cannot make songDao statement");
		}
		
		return list;
	}

	@Override
	public List<ISong> getUserLikedSongs(Integer userId) {
		List<ISong> list = new ArrayList<ISong>();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_ALL_LIKED, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userId);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				ISong song = new Song(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("artist"), resultSet.getString("genre"), resultSet.getInt("uploader_id"),
						resultSet.getString("song_path"));
				list.add(song);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot get all user liked songs!");
		}

		return list;
	}
	
	@Override
	public List<ISong> getSongsBySearchText(String searchText) {
		List<ISong> list = new ArrayList<>();
		try {
			PreparedStatement ps1 = connection.prepareStatement(SELECT_BY_SEARCH_TEXT,Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1, "%"+searchText+"%");
			ps1.setString(2, "%"+searchText+"%");
			
			ResultSet resultSet = ps1.executeQuery();
			while (resultSet.next()) {
				ISong song = new Song(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("artist"),
						resultSet.getString("genre"), resultSet.getInt("uploader_id"),
						resultSet.getString("song_path"));
				list.add(song);
			}

		} catch (SQLException e) {
			System.out.println("Cannot make songDao statement");
		}
		
		return list;
	}

	@Override
	public Song getLastAdded() {
		Song song = null;
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_LASTLY_ADDED, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString(1);
				String artist = resultSet.getString(2);
				String path = resultSet.getString(3);
				song = new Song(name, artist, path);
				song.setName(name);
				song.setArtist(artist);
				song.setSongPath(path);
			}
		} catch (SQLException e) {
			System.out.println("Cannot make songDao statement");
		}

		return song;

	}


	@Override
	public List<ISong> getSongsInPlaylist(Integer playlistId) {
		List<ISong> list = new ArrayList<>();
		try {
			PreparedStatement ps1 = connection.prepareStatement(SELECT_SONG_BY_PLAYLIST,Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, playlistId);
			
			ResultSet resultSet = ps1.executeQuery();
			while (resultSet.next()) {
				ISong song = new Song(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("artist"),
						resultSet.getString("genre"), resultSet.getInt("uploader_id"),
						resultSet.getString("song_path"));
				list.add(song);
			}

		} catch (SQLException e) {
			System.out.println("Cannot make songDao statement");
		}
		
		return list;
	}
}
package com.feelthesound.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.feelthesound.model.ISong;
import com.feelthesound.model.Song;
import com.feelthesound.model.exceptions.SongException;

@Component
public class SongDAO implements ISongDAO {
	

	private Map<String, ISong> songsNames = new HashMap<String,ISong>(); //song name -> song
	
	private static final String SELECT_BY_SEARCH_TEXT = "SELECT * FROM feelthesound.songs WHERE name LIKE ? OR artist LIKE ? ";
	private static final String SELECT_BY_USER = "SELECT * FROM feelthesound.songs WHERE uploader_id=?";
	private static final String SELECT_ALLS_SONGS = "SELECT id, uploader_id, song_path, name, artist, genre, upload_date FROM feelthesound.songs";
	private static final String INSERT_SONG = "INSERT INTO feelthesound.songs(id, name, artist, upload_date, genre, uploader_id, song_path) VALUES (null, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_IF_SONG_IS_INSERTED = "SELECT count(*) FROM feelthesound.songs WHERE name = ? AND artist = ?";
	private static final String DELETE_SONG = "DELETE FROM feelthesound.songs WHERE name = ? AND artist = ? AND uploader_id = ?";
	private static final String SELECT_ALL_LIKED = "SELECT * FROM feelthesound.songs JOIN feelthesound.likes ON songs.id = likes.song_id AND likes.user_id = ?";
	
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

	// getting all the songs from the database
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.DAOs.ISongDAO#getAllSongs()
	 */
	@Override
	public Map<String, ISong> getAllSongs() {
	//	Map<String, ISong> songsArtists = new HashMap<String,ISong>(); //song artist -> song
		
		try {
			Statement st = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery(SELECT_ALLS_SONGS);
			while (resultSet.next()) {
				ISong song = new Song(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("artist"),
						resultSet.getString("genre"), resultSet.getInt("uploader_id"),
						resultSet.getString("song_path"));
				
				songsNames.put(song.getName(), song);
			//	songsArtists.put(song.getArtist(), song);
			}

		} catch (SQLException e) {
			System.out.println("Cannot make songDao statement");
		}

		return songsNames;
	}
	
	public List<ISong> getAllSongsWithPrefix(String prefix){
		
		Map<String, ISong> songs = getAllSongs();
		
		for (String name : songs.keySet()) {
			System.err.println(name);
		}
		
		if ( (prefix == null) || (prefix.trim().length() == 0) )
			return new ArrayList<ISong>();
		
		List<ISong> result = new LinkedList<ISong>();
		
		for (String name : songs.keySet()) {
			if (name.startsWith(prefix)) {
				result.add(songs.get(name));
			}
		}
		
		return result;
		
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
}
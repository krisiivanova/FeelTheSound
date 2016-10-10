package com.feelthesound.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.feelthesound.model.Song;

@Component
public class SongDAO implements ISongDAO{
	private static final String SELECT_ALLS_SONGS = "SELECT song_id, user_id, song_path, name, artist, genre, uploadDate";
	private static final String INSERT_SONG ="INSERT INTO feelthesound.songs(user_id, song_path, name, artist, genre, uploadDate) VALUES (?,?,?,?,?,?)";

	
    private static final String SELECT_IF_SONG_IS_INSERTED = "SELECT count(*) FROM feelthesound.songs WHERE song_name = ? AND artist_name = ?";
	private static final String DELETE_SONG= "DELETE FROM feelthesound.songs WHERE song_name = ? AND artist_name = ? AND uploader_id = ?";
	private static final String SELECT_SONG_BY_NAME = "SELECT * from feelthesound.songs WHERE song_name LIKE ? ORDER BY postdate ";
	private static final String SELECT_SONG_BY_ARTIST = "SELECT * from feelthesound.songs WHERE artist_name LIKE ? ORDER BY postdate ";
	private static final String SELECT_ALL_SONG_LIKES = "SELECT COUNT(id) FROM feelthesound.likes WHERE song_id = ?";
	private static final String INSERT_LIKE = "INSERT into feelthesound.likes (user_id, song_id) values(?,?)";
	private static final String SELECT_LIKES_COUNT  = "SELECT COUNT(id) from feelthesound.likes WHERE user_id = ? AND song_id = ?";
	
	
	
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
	
	//getting all the songs from the database
	/* (non-Javadoc)
	 * @see com.feelthesound.model.DAOs.ISongDAO#getAllSongs()
	 */
	@Override
	public List<Song> getAllSongs() {
		List<Song> songs = new ArrayList<Song>();
		try {
			Statement st = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery(SELECT_ALLS_SONGS);
			while (resultSet.next()) {

				songs.add(new Song(resultSet.getInt("song_id"), resultSet.getInt("user_id"),
						resultSet.getString("song_path"), resultSet.getString("name"), resultSet.getString("artist"),
						resultSet.getString("genre"), resultSet.getTimestamp("uploadDate")));
			}

		} catch (SQLException e) {
			System.out.println("Cannot make songDao statement");
		}

		return songs;
	}
	
	//inserting a song into the database
	/* (non-Javadoc)
	 * @see com.feelthesound.model.DAOs.ISongDAO#insertSong(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date)
	 */
	@Override
	public int insertSong(int userId, String songPath, String name, String artist, String genre, Date uploadDate) {
		int songId = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_SONG, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userId);
			ps.setString(2, songPath);
			ps.setString(3, name);
			ps.setString(4, artist);
			ps.setString(5, genre);
			Date utilDate = uploadDate;
			Timestamp time = new Timestamp(utilDate.getTime());
			ps.setTimestamp(6, time);

			songId = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error with inserting song");
			e.printStackTrace();
		}

		return songId;
	}
	
	
//	public static List<Song> searchForSongsByNameByDate(String songName){
//		List<Song> result = new ArrayList<Song>();
//		PreparedStatement ps = null;
//		try {
//		    ps = connection.prepareStatement(SELECT_SONG_BY_NAME);
//			ps.setString(1, "%"+songName+"%");
//
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()){
//				try {
//					result.add(new Song(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getString(5),  rs.getInt(4)));
//				} catch (SongException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("Unable to connect to database!");
//		}
//		
//		return result;
//	}
//	
//	public static List<Song> searchForSongsByArtistByDate(String artistName){
//		List<Song> result = new ArrayList<Song>();
//		PreparedStatement ps = null;
//		try {
//			ps = connection.prepareStatement(SELECT_SONG_BY_ARTIST);
//			ps.setString(1, "%"+artistName+"%");
//
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()){
//				try {
//					result.add(new Song(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getString(5),  rs.getInt(4)));
//				} catch (SongException e) {
//					e.printStackTrace();
//				}
//			}
//
//		} catch (SQLException e) {
//			System.out.println("Unable to connect to database!");
//		}
//		
//		return result;
//	}
	
}

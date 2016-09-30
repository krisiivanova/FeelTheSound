package com.soundcloud.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.soundcloud.model.IUser;
import com.soundcloud.model.Song;
import com.soundcloud.model.exceptions.ConnectionException;
import com.soundcloud.model.exceptions.SongException;
import com.soundcloud.model.exceptions.UserException;

public class SongDAO {
	
	private static final String SELECT_IF_SONG_IS_INSERTED = "SELECT count(*) FROM soundcloud.songs WHERE song_name = ? AND artist_name = ?";
	private static final String INSERT_SONG = "INSERT INTO soundcloud.songs VALUES (null, ?, ?, ?, ?, ?);";
	private static final String DELETE_SONG= "DELETE FROM soundcloud.songs WHERE song_name = ? AND artist_name = ? AND uploader_id = ?";
	private static final String SELECT_SONG_BY_NAME = "SELECT * from soundcloud.songs WHERE song_name LIKE ? ORDER BY postdate ";
	private static final String SELECT_SONG_BY_ARTIST = "SELECT * from soundcloud.songs WHERE artist_name LIKE ? ORDER BY postdate ";
	private static final String SELECT_ALL_SONG_LIKES = "SELECT COUNT(id) FROM soundcloud.likes WHERE song_id = ?";
	private static final String INSERT_LIKE = "INSERT into soundcloud.likes (user_id, song_id) values(?,?)";
	private static final String SELECT_LIKES_COUNT  = "SELECT COUNT(id) from soundcloud.likes WHERE user_id = ? AND song_id = ?";
	
	public int insertSong(Song song) throws ConnectionException, SongException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			
			PreparedStatement ps1 = connection.prepareStatement(SELECT_IF_SONG_IS_INSERTED, Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1, song.getName());
			ps1.setString(2, song.getArtist());
			
			ResultSet rs = ps1.executeQuery();
			rs.next();
			int isAlreadyInserted = rs.getInt(1);
			
			if(isAlreadyInserted >= 1){
				//or just showing message
				throw new SongException("This song has already been uploaded!");
			}
			
			PreparedStatement ps = connection.prepareStatement(INSERT_SONG, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, song.getArtist());
			ps.setString(2, song.getName());
			ps.setString(4, song.getGenre());
			ps.setInt(3, song.getUserId());
			ps.setTimestamp(5, Timestamp.valueOf(song.getUploadDate()));
			ps.executeUpdate();

			ResultSet rs1 = ps.getGeneratedKeys();
			rs1.next();
			
			return rs1.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SongException("Song insertion failed!");
		}
	}
	
	public void deleteSong(Song song) throws UserException, ConnectionException{
		Connection connection = DBConnection.getInstance().getConnection();
		try{
			PreparedStatement ps = connection.prepareStatement(DELETE_SONG, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, song.getName());
			ps.setString(2, song.getArtist());
			ps.setInt(3, song.getUserId());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("Song deletion failed!");
		}
	}
	
	
	public static List<Song> searchForSongsByNameByDate(String songName) throws ConnectionException, SongException{
		Connection connection = DBConnection.getInstance().getConnection();
		List<Song> result = new ArrayList<Song>();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_SONG_BY_NAME);
			ps.setString(1, "%"+songName+"%");

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				result.add(new Song(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getString(5),  rs.getInt(4)));
			}
			
		} catch (SQLException e) {
			throw new ConnectionException("Unable to connect to database!", e);
		}
		
		return result;
	}
	
	public static List<Song> searchForSongsByArtistByDate(String artistName) throws ConnectionException, SongException{
		Connection connection = DBConnection.getInstance().getConnection();
		List<Song> result = new ArrayList<Song>();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_SONG_BY_ARTIST);
			ps.setString(1, "%"+artistName+"%");

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				result.add(new Song(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getString(5),  rs.getInt(4)));
			}

		} catch (SQLException e) {
			throw new ConnectionException("Unable to connect to database!", e);
		}
		
		return result;
	}
	
	public int getSongLikes(Song song) throws ConnectionException{
		Connection connection = DBConnection.getInstance().getConnection();
		int result = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SONG_LIKES);
			ps.setInt(1, song.getId());
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			result =  rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void likeSong(int userId, int songId) throws ConnectionException{
		Connection connection = DBConnection.getInstance().getConnection();
		
		try {
			 PreparedStatement statement = connection.prepareStatement(SELECT_LIKES_COUNT);
			 statement.setInt(1, userId);
			 statement.setInt(2, songId);
			 
			ResultSet rs = statement.executeQuery();
			rs.next();
			int isAlredyLiked = rs.getInt(1);
			
			if(isAlredyLiked >= 1) return;
			
			PreparedStatement st = connection.prepareStatement(INSERT_LIKE);
			st.setInt(1, songId);
			st.setInt(2, userId);
			
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

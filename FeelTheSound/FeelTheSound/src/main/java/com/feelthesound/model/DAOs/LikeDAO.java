package com.feelthesound.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.feelthesound.model.Like;
import com.feelthesound.model.exceptions.LikeException;

public class LikeDAO implements ILikeDAO {
	private static final String SELECT_ALL_SONGS = "SELECT song_id, user_id FROM feelthesound.likes";
	private static final String INSERT_LIKE_TO_A_SONG = "INSERT INTO feelthesound.likes(song_id, user_id) VALUES (?,?)";
	private static final String DELETE_LIKE_OF_A_SONG = "DELETE FROM feelthesoud.likes WHERE song_id = ? AND user_id = ?";
	private static final String CHECK_IF_SONG_IS_LIKED = "IF EXISTS (SELECT * FROM feelthesound.likes WHERE song_id=? AND user_id = ?)BEGIN INSERT_LIKE_TO_A_SONG END ELSE BEGIN DELETE_LIKE_OF_A_SONG END";

	private static volatile ILikeDAO likeDAO;
	Connection con = DBConnection.getInstance().getConnection();

	public static ILikeDAO getInstance() {
		if (likeDAO == null) {
			synchronized (LikeDAO.class) {
				if (likeDAO == null) {
					likeDAO = new LikeDAO();
				}
			}
		}
		return LikeDAO.likeDAO;
	}

	// getting the likes for all songs
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.DAOs.ILikeDAO#getAllSongsLikes()
	 */
	@Override
	public Set<Like> getAllSongsLikes() throws LikeException {
		Set<Like> likes = new HashSet<Like>();
		try {
			Statement st = DBConnection.getInstance().getConnection().createStatement();
			ResultSet resultSet = st.executeQuery(SELECT_ALL_SONGS);
			while (resultSet.next()) {
				likes.add(new Like(resultSet.getInt("song_id"), resultSet.getInt("user_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LikeException("Couldnt get all the likes");
		}

		return likes;
	}

	// like song by a certain user
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.DAOs.ILikeDAO#likeSong(int, int)
	 */
	@Override
	public void likeSong(int songId, int userId) throws LikeException {
		try {
			PreparedStatement ps = con.prepareStatement(INSERT_LIKE_TO_A_SONG);
			ps.setInt(1, songId);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LikeException("Couldnt like the song");
		}
	}

	// dislike song by a certain user
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feelthesound.model.DAOs.ILikeDAO#dislikeSong(int, int)
	 */
	@Override
	public void dislikeSong(int songId, int userId) throws LikeException {
		try {
			PreparedStatement ps = con.prepareStatement(DELETE_LIKE_OF_A_SONG);
			ps.setInt(1, songId);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LikeException("Couldnt dislike the song");
		}
	}
}

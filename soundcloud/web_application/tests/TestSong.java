package com.soundcloud.tests;

import static org.junit.Assert.assertNotEquals;

import java.util.Random;

import org.junit.Test;

import com.soundcloud.model.Song;
import com.soundcloud.model.User;
import com.soundcloud.model.DAO.SongDAO;
import com.soundcloud.model.DAO.UserDAO;
import com.soundcloud.model.exceptions.ConnectionException;
import com.soundcloud.model.exceptions.SongException;
import com.soundcloud.model.exceptions.UserException;

public class TestSong {
	
	private SongDAO songDao = new SongDAO();
	private UserDAO userDao = new UserDAO();
	
	@Test
	public void testSong() throws UserException, ConnectionException, SongException {
		int num = new Random().nextInt(5000);
		
		int userId = userDao.registerUser(new User(0, "krisi-212", "1232abcABC", "krisi212@abv.bg"));
		assertNotEquals(userId, 0);

		//song insert test
		Song song = new Song(0, "Love on topp", "Beyonce", "pop", userId);
		songDao.insertSong(song);

		// song deletion test
		songDao.deleteSong(new Song(0, "Love on topp", "Beyonce", "pop", userId));
		
		//search song by name
		SongDAO.searchForSongsByNameByDate("Love on topp");
		
		//search songs by artist
		SongDAO.searchForSongsByArtistByDate("Beyonce");
		
		Song song1 = new Song(0, "Love on topp", "Beyonce", "pop", userId);
		songDao.getSongLikes(song1);
		
		songDao.likeSong(userId, song1.getId());
	
	}
}

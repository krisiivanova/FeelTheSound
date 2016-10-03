package com.feelthesound.tests;


import static org.junit.Assert.assertNotEquals;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.feelthesound.model.FeelTheSoundConfiguration;
import com.feelthesound.model.Song;
import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.ISongDAO;
import com.feelthesound.model.DAOs.IUserDAO;
import com.feelthesound.model.DAOs.SongDAO;
import com.feelthesound.model.DAOs.UserDAO;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.SongException;
import com.feelthesound.model.exceptions.UserException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FeelTheSoundConfiguration.class)
public class TestSong {
	
	private ISongDAO songDao = new SongDAO();
	private IUserDAO userDao = new UserDAO();
	
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

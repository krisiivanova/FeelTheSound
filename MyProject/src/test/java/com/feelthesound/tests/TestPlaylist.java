package com.feelthesound.tests;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.feelthesound.model.FeelTheSoundConfiguration;
import com.feelthesound.model.Playlist;
import com.feelthesound.model.Song;
import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.IPlaylistDAO;
import com.feelthesound.model.DAOs.IUserDAO;
import com.feelthesound.model.DAOs.PlaylistDAO;
import com.feelthesound.model.DAOs.UserDAO;
import com.feelthesound.model.exceptions.ConnectionException;
import com.feelthesound.model.exceptions.SongException;
import com.feelthesound.model.exceptions.UserException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FeelTheSoundConfiguration.class)
public class TestPlaylist {
	
	IUserDAO userDao = new UserDAO();
	IPlaylistDAO playlistDao = new PlaylistDAO();
	
	@Test
	public void testPLaylist() throws UserException, SongException, ConnectionException{
		User user = new User(0, "krisitooo","abcABC123", "krisitooo@abv.bg");
		int userId = userDao.registerUser(user);
		
		User user1 = new User(0, "mimi","abcABC123", "mimi123@abv.bg");
		int user1Id = userDao.registerUser(user1);
		
		Playlist playlist = new Playlist(0, "relaxing music", userId);
		playlistDao.addNewPlaylist(playlist);
		
				
		Song song = new Song(0, "Dust in the wind", "Kansas", "rock", user1Id);
		playlistDao.addSongIntoPlaylist(playlist, song.getId());
		
		
	}
}

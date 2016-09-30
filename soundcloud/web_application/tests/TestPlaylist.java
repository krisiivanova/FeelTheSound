package com.soundcloud.tests;

import org.junit.Test;

import com.soundcloud.model.Playlist;
import com.soundcloud.model.Song;
import com.soundcloud.model.User;
import com.soundcloud.model.DAO.PlaylistDAO;
import com.soundcloud.model.DAO.UserDAO;
import com.soundcloud.model.exceptions.ConnectionException;
import com.soundcloud.model.exceptions.SongException;
import com.soundcloud.model.exceptions.UserException;

public class TestPlaylist {
	UserDAO userDao = new UserDAO();
	PlaylistDAO playlistDao = new PlaylistDAO();
	
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

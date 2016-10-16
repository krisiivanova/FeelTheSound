
package com.feelthesound.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.FeelTheSoundConfiguration;
import com.feelthesound.model.DAOs.IPlaylistDAO;
import com.feelthesound.model.DAOs.IUserDAO;
import com.feelthesound.model.exceptions.PlaylistException;
import com.feelthesound.model.exceptions.UserException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FeelTheSoundConfiguration.class)
public class TestPlaylist {
	
	@Autowired	
	IPlaylistDAO playlistDao;
	
	@Autowired	
	IUserDAO userDao;
	
	@Test
	public void testPlaylist() throws PlaylistException, UserException{
		User user = userDao.getUserByUsername("mimi");
		
		playlistDao.addPlaylist("relaxing", user);
		
		playlistDao.ifPlaylistExist(user, "relaxing");
		
		playlistDao.deletePlaylist(1);

		playlistDao.getPlaylistSongs(2);
			
	}
}

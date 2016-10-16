package com.feelthesound.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.feelthesound.model.ISong;
import com.feelthesound.model.DAOs.FeelTheSoundConfiguration;
import com.feelthesound.model.DAOs.ISongDAO;
import com.feelthesound.model.exceptions.SongException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FeelTheSoundConfiguration.class)
public class TestSong {

	@Autowired
	ISongDAO songDao;

	@Test
	public void testSong() throws SongException {
		ISong song = songDao.getLastAdded();

		songDao.deleteSong(song);

		songDao.getSongsBySearchText("pop");

		songDao.getSongsByUser(2);

		songDao.getSongsInPlaylist(1);

		songDao.insertSong(song);

	}
}

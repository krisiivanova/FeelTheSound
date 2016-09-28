package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import model.Playlist;
import model.PlaylistDAO;
import model.Song;
import model.SongDAO;
import model.User;
import model.UserDAO;
import model.UserException;

public class TestUser {
	private UserDAO userDao = new UserDAO();
	private SongDAO songDao = new SongDAO();
	private PlaylistDAO playlistDao = new PlaylistDAO();
	
	@Test
	public void testUser() throws UserException {
		int num = new Random().nextInt(5000);
		
		int id = userDao.registerUser(new User(0, "Gosho" + num, "123abc"));
		
		assertNotEquals(id, 0);
		
		int userid = userDao.loginUser(new User(0, "Gosho" + num, "123abc"));
		
		assertEquals(id, userid);
		
//		boolean result = userDao.deleteUser(userid);
//		
//		assertTrue(result);
		
		boolean isExisting = userDao.isUserExisting(new User(0, "Gosho" + num, "123abc"));
		
		assertTrue(isExisting);
		
		Song song = new Song(0, "asdasd", "adasdasd", userid);
		int songid = songDao.insertSong(song);
		
		
		int playlistid = playlistDao.addNewPlaylist(new Playlist(0,"pop",userid));
		
//		playlistDao.addSongIntoPlaylist(playlistid,songid);
//		assertEquals(id, userid);
		
//      songDao.deleteSong(song);
      
        userDao.like(userid, songid);
      
        userDao.like(userid, songid);
        
      //  userDao.unFollow(3, 7);
        
        
        userDao.insertProfilePhoto(userid, "sadass");
        
      
		
	}
}

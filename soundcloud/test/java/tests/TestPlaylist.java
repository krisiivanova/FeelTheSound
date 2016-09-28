package tests;

import model.Playlist;
import model.Song;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlaylist {
	//private PlaylistDAO pDao = new PlaylistDAO();
	
	@Test
	public void testAddQuestion() {
		
		Playlist playlist = new Playlist(0, "pop");
		playlist.addSong(new Song(0, "Beyonce", "Love on top"));
		playlist.addSong(new Song(0, "Arctic Monkeys", "I Wanna Be Yours"));
		playlist.addSong(new Song(0, "Oasis", "Wanderwall"));
		playlist.addSong(new Song(0, "Coldplay", "Magic"));
				
	//	pDao.addSongIntoPlaylist(playlist);
	}
}

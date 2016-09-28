package model;


public class User {
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String country;
	private String city;
	private String photo;
	
//	private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
//	private HashSet<User> following = new HashSet<User>();
//	private HashSet<User> followers = new HashSet<User>();
//	private ArrayList<Song> uploadedSongs = new ArrayList<Song>();
//	private HashSet<Song> likedSongs = new HashSet<Song>();
			
	
	public User(int id, String username, String password) {
		this.id = id;
		this.setUsername(username);
		this.setPassword(password);
	}
	
	private boolean isValidString(String string) {
		return (string != null) && (string.length() > 0);
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (isValidString(username)) {
			this.username = username;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (isValidString(password)) {
			this.password = password;
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

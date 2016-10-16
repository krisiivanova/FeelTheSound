package com.feelthesound.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feelthesound.model.IPlaylist;
import com.feelthesound.model.ISong;
import com.feelthesound.model.User;
import com.feelthesound.model.DAOs.IPlaylistDAO;
import com.feelthesound.model.DAOs.ISongDAO;
import com.feelthesound.model.DAOs.IUserDAO;
import com.feelthesound.model.exceptions.UserException;

@Controller
public class ProfileController {
	
	
	private Pattern pattern;
	private Matcher matcher;

	private static final String NAME_PATTERN = "^[\\p{L} .'-]+$";
	private static final String FIRST_NAME_LENGTH = "First name can't be longer than 25 symbols";
	private static final String FIRST_NAME_CONTENT = "First name must contains only letters";
	private static final String LAST_NAME_LENGTH = "Last name can't be longer than 25 symbols";
	private static final String LAST_NAME_CONTENT = "Last name must contains only letters";
	private static final String CITY_NAME_LENGTH = "City name can't be longer than 25 symbols";
	private static final String CITY_NAME_CONTENT = "City name must contains only letters";
	private static final String COUNTRY_NAME_LENGTH = "Country name can't be longer than 25 symbols";
	private static final String COUNTRY_NAME_CONTENT = "Country name must contains only letters";
	
	
	
	
	

	@Autowired
	ISongDAO songDao;

	@Autowired
	IUserDAO userDao;

	@Autowired
	IPlaylistDAO playlistDao;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session) {
		try {
			User userInSession = (User) session.getAttribute("user");

			userInSession.setPhoto(userDao.getProfilePhoto(userInSession));
			int userId = userDao.getUserById(userInSession);
			userInSession.setId(userId);
			
			model.addAttribute("user", userInSession);
			

		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}

		return "profile";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/songs")
	public ModelAndView getSubView(@RequestParam("userId") Integer userId, Model model) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("songsList");

			List<ISong> userSongs = songDao.getSongsByUser(userId);

			modelAndView.addObject("songs", userSongs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/liked")
	public ModelAndView getLiked(@RequestParam("userId") Integer userId, Model model) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("songsList");

			System.out.println("USER ID : " + userId);

			List<ISong> userLikedSongs = songDao.getUserLikedSongs(userId);

			modelAndView.addObject("songs", userLikedSongs);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/myPlaylists")
	public ModelAndView getAllPlaylists(@RequestParam("userId") Integer userId, Model model) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("playlists");

			System.out.println("USER ID : " + userId);

			List<IPlaylist> playlists = playlistDao.getAllPlaylists(userId);

			modelAndView.addObject("playlists", playlists);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String editProfile(@RequestParam(value = "first_name") String first_name,
			@RequestParam(value = "last_name") String last_name, @RequestParam(value = "city") String city,
			@RequestParam(value = "country") String country,
			HttpServletRequest request, Model model) throws UserException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		User user = userDao.getUserByUsername(username);
		model.addAttribute("user", user);

		if (username == null || username.isEmpty()) {
			return "index";
		} else {
			String message = editProfileInfo(request, first_name, last_name, country, city);
			model.addAttribute("Message", message);
			return "profile";
		}
	}
	
	
	private String editProfileInfo(HttpServletRequest request, String f_name, String l_name, String country,
			String city) throws UserException {

		String message;

		if (!f_name.isEmpty() && f_name != null) {
			pattern = Pattern.compile(NAME_PATTERN, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(f_name);
			if (matcher.find()) {
				if (f_name.length() <= 25) {
					try {
						userDao.editFirstName((String) request.getSession().getAttribute("username"), f_name);
					} catch (Exception e) {
						message = "First name failed to be changed";
						return message;
					}
				} else {
					message = FIRST_NAME_LENGTH;
					return message;
				}
			} else {
				message = FIRST_NAME_CONTENT;
				return message;
			}
		}

		if (!l_name.isEmpty() && l_name != null) {
			pattern = Pattern.compile(NAME_PATTERN, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(l_name);
			if (matcher.find()) {
				if (l_name.length() <= 25) {
					try {
						userDao.editLastName(((String) request.getSession().getAttribute("username")), l_name);
						System.out.println("Edited last name");
					} catch (Exception e) {
						message = "Last name failed to be changed";
						return message;
					}
				} else {
					message = LAST_NAME_LENGTH;
					return message;
				}
			} else {
				message = LAST_NAME_CONTENT;
				return message;
			}
		}

		if (!country.isEmpty() && country != null) {
			pattern = Pattern.compile(NAME_PATTERN, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(country);
			if (matcher.find()) {
				if (country.length() <= 25) {
					try {
						userDao.editCountry(((String) request.getSession().getAttribute("username")),
								country);
					} catch (Exception e) {
						message = "Country name failed to be changed";
						return message;
					}
				} else {
					message = COUNTRY_NAME_LENGTH;
					return message;
				}
			} else {
				message = COUNTRY_NAME_CONTENT;
				return message;
			}
		}

		if (!city.isEmpty() && city != null) {
			pattern = Pattern.compile(NAME_PATTERN, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(city);
			if (matcher.find()) {
				if (city.length() <= 25) {
					try {
						userDao.editCity(((String) request.getSession().getAttribute("username")),
								city);
					} catch (Exception e) {
						message = "City name failed to be changed";
						return message;
					}
				} else {
					message = CITY_NAME_LENGTH;
					return message;
				}
			} else {
				message = CITY_NAME_CONTENT;
				return message;
			}
		}

		if ((city.isEmpty() || city == null) && (country.isEmpty() || country == null)
				&& (l_name.isEmpty() || l_name == null) && (f_name.isEmpty() || f_name == null)) {
			message = "Nothing for edit";
			return message;
		}

		message = "Successfully edit profile info";
		
		return message;
	}
	
}

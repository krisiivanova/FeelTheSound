DROP DATABASE IF EXISTS feelthesound;
CREATE DATABASE feelthesound;
USE feelthesound;

CREATE TABLE users(
id SERIAL,
username VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(300) NOT NULL,
email VARCHAR(50) NOT NULL UNIQUE,
first_name VARCHAR(30),
last_name VARCHAR(30),
country VARCHAR(25),
city VARCHAR(25),
profile_photo VARCHAR(300),
PRIMARY KEY(id)
);

CREATE TABLE songs(
id SERIAL,
name VARCHAR(100) NOT NULL,
artist VARCHAR(100) NOT NULL,
upload_date TIMESTAMP,
genre VARCHAR(40) NOT NULL,
uploader_id BIGINT UNSIGNED NOT NULL,
song_path VARCHAR(300) NOT NULL,
CONSTRAINT user_upload_song FOREIGN KEY (uploader_id) REFERENCES users(id)
);

CREATE TABLE playlists(
id SERIAL,
name VARCHAR(60) NOT NULL,
user_id BIGINT UNSIGNED NOT NULL,
PRIMARY KEY (id),
CONSTRAINT user_has_this_playlist FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE playlist_with_songs(
playlist_id BIGINT UNSIGNED,
song_id BIGINT UNSIGNED,
PRIMARY KEY (playlist_id, song_id),
CONSTRAINT playlist_has_this_song FOREIGN KEY (playlist_id) REFERENCES playlists(id),
CONSTRAINT song_in_this_playlist FOREIGN KEY (song_id) REFERENCES songs(id)
);

CREATE TABLE follows(
follower_id BIGINT UNSIGNED,
following_id BIGINT UNSIGNED,
PRIMARY KEY (follower_id, following_id),
CONSTRAINT user_who_is_followed FOREIGN KEY (following_id) REFERENCES users(id),
CONSTRAINT follower_of_user FOREIGN KEY (follower_id) REFERENCES users(id)
);

CREATE TABLE likes(
song_id BIGINT UNSIGNED,
user_id BIGINT UNSIGNED,
PRIMARY KEY(song_id, user_id),
CONSTRAINT song_which_is_liked FOREIGN KEY (song_id) REFERENCES songs(id),
CONSTRAINT user_who_likes_this_song FOREIGN KEY (user_id) REFERENCES users(id)
);


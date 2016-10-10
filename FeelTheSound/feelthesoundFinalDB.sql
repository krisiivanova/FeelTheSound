DROP DATABASE IF EXISTS feelthesound;
CREATE DATABASE feelthesound;
USE feelthesound;

CREATE TABLE users(
user_id SERIAL,
password VARCHAR(100) NOT NULL,
email VARCHAR(25) UNIQUE,
username VARCHAR(30) UNIQUE,
first_name VARCHAR(25),
last_name VARCHAR(25),
city VARCHAR(25),
country VARCHAR(25),
photo_path VARCHAR(300),
PRIMARY KEY(user_id)
);
 
CREATE TABLE songs(
song_id SERIAL,
user_id BIGINT UNSIGNED,
song_path VARCHAR(300),
name VARCHAR(100),
artist VARCHAR(100),
genre VARCHAR(50),
uploadDate TIMESTAMP,
PRIMARY KEY (song_id),
CONSTRAINT user_upload_song FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE playlists(
playlist_id SERIAL,
title VARCHAR(50),
user_id BIGINT UNSIGNED,
PRIMARY KEY (playlist_id),
CONSTRAINT user_has_this_playlist FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE playlist_with_songs(
playlist_id BIGINT UNSIGNED,
song_id BIGINT UNSIGNED,
PRIMARY KEY (playlist_id, song_id),
CONSTRAINT playlist_has_this_song FOREIGN KEY (playlist_id) REFERENCES playlists(playlist_id),
CONSTRAINT song_in_this_playlist FOREIGN KEY (song_id) REFERENCES songs(song_id)
);

CREATE TABLE follows(
follower_id BIGINT UNSIGNED,
followed_id BIGINT UNSIGNED,
PRIMARY KEY (follower_id, followed_id),
CONSTRAINT user_who_is_followed FOREIGN KEY (followed_id) REFERENCES users(user_id),
CONSTRAINT follower_of_user FOREIGN KEY (follower_id) REFERENCES users(user_id)
);

CREATE TABLE likes(
song_id BIGINT UNSIGNED,
user_id BIGINT UNSIGNED,
PRIMARY KEY(song_id, user_id),
CONSTRAINT song_which_is_liked FOREIGN KEY (song_id) REFERENCES songs(song_id),
CONSTRAINT user_who_likes_this_song FOREIGN KEY (user_id) REFERENCES users(user_id)
);

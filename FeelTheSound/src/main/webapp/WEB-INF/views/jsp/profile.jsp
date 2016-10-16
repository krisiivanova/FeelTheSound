<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="javax.websocket.Session"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.feelthesound.model.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FeelTheSound</title>
<link rel="icon" href="images/tab.png">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="css/style.css" rel="stylesheet">
</head>

<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" a href="./home">FeelTheSound</a>
			</div>
			<ul class="nav navbar-nav">

				<li class="active"><a href="./profile">Profile</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Edit profile<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="./uploadFile">Change profile photo</a></li>
						<li><a href="./editProfile">Edit profile</a></li>
					</ul></li>

				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Options<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="./uploadMusic">Upload Song</a></li>
						<li><a href="./Playlist">Create Playlist</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="./Logout"><span
						class="glyphicon glyphicon-log-in"></span> Log out</a></li>
			</ul>
		</div>
	</nav>

	<div class="row">
		<div class="col-lg-5">
			<div class="media">
				<center>
					<h1 class="media-heading"><h1>Welcome, ${user.username}!<br><br></h1></h1>
				</center>

				<center> <img class="media-object dp img-circle"
					src="./profilePhotos/${user.profilePhoto}"
					style="width: 200px; height: 200px;" /><br></center>
				<div class="media-body">
				</div>
			</div>
		</div>
	</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="panel-body">

				<button class="btn btn-info btn-pressure btn-sensitive"
					style="background-color: black" onclick="getSongs(${user.id})">My
					Songs</button>

				<button class="btn btn-info btn-pressure btn-sensitive"
					style="background-color: black" onclick="getLiked(${user.id})">Liked
					Songs</button>

				<button class="btn btn-info btn-pressure btn-sensitive"
					style="background-color: black" onclick="getPlaylists(${user.id})">My
					playlists</button>
			</div>
		</div>
	</div>

	<div id="mySongsDiv"></div>

	<div id="likedSongsDiv"></div>

	<div id="playlistsDiv"></div>

</body>

<script>
function getSongs(userId){
	console.log("User : "+userId);
	$.ajax({
		url: "./songs",
		type:"GET",
		datatype: 'html',
		data:{
			userId: userId,
		},
		success: function(data){
			$("#likedSongsDiv").empty();
			$("#playlistsDiv").empty();
			$("#mySongsDiv").empty();
			$("#mySongsDiv").append(data);
		}
	});
}

function getLiked(userId){
	$.ajax({
		url: "./liked",
		type:"GET",
		datatype: 'html',
		data:{
			userId: userId,
		},
		success: function(data){
			console.log(data);
			$("#mySongsDiv").empty();
			$("#playlistsDiv").empty();
			$("#likedSongsDiv").empty();
			$("#likedSongsDiv").append(data);
		}
	});
}

function getPlaylists(userId){
	console.log("User : "+userId);
	$.ajax({
		url: "./myPlaylists",
		type:"GET",
		datatype: 'html',
		data:{
			userId: userId,
		},
		success: function(data){
			$("#mySongsDiv").empty();
			$("#likedSongsDiv").empty();
			$("#playlistsDiv").empty();
			$("#playlistsDiv").append(data);
		}
	});
}

</script>
</html>
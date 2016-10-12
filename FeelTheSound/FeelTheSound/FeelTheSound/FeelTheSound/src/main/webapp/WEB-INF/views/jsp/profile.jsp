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
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" a href="./home">FeelTheSound</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="./profile">Profile</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="./uploadFile">Change profile photo</a></li>
					<li><a href="./editProfile">Edit profile</a></li>
					<li><a href="./uploadMusic">Upload Song</a></li>
					<li><a href="./Logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>


	<div class="row">
		<div class="col-lg-5">
			<div class="media">
				<a class="pull-left"> <img class="media-object dp img-circle"
					src="./profilePhotos/${user.profilePhoto}"
					style="width: 200px; height: 200px;" /><br></a> <br> <br>
				<div class="media-body">
					<center>
						<h4 class="media-heading">Welcome, ${user.username}!</h4>
					</center>
					<div class="col-md-7 user-details">
						<div align="justify">
							<h3 style="color: black;">FOLLOWERS</h3>
							<p style="color: black;">${followers}</p>
							<br>

							<h3 style="color: black;">FOLLOWING</h3>
							<h4 style="color: black;">${following}</h4>
						</div>

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
					style="background-color: black">My playlist</button>
			</div>
		</div>
	</div>

	<div id="mySongsDiv"></div>

	<div id="likedSongsDiv"></div>

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
			console.log(data);
			$("#mySongsDiv").empty();
			$("#mySongsDiv").append(data);
		}
	});
}

function getLiked(userId){
	console.log("User : "+userId);
	$.ajax({
		url: "./liked",
		type:"GET",
		datatype: 'html',
		data:{
			userId: userId,
		},
		success: function(data){
			console.log(data);
			$("#likedSongsDiv").empty();
			$("#likedSongsDiv").append(data);
		}
	});
}
</script>
</html>





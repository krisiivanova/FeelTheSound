<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="javax.websocket.Session"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.feelthesound.model.*"%>
<%@page import="com.feelthesound.DAOs.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FeelTheSound</title>
<link rel="icon" href="images/tab.png">
<link href="css/bootstrap.min.css" rel="stylesheet">
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
				<a class="navbar-brand" a href="home">FeelTheSound</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Profile</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							Edit profile</a></li>
					<li><a href=""><span class="glyphicon glyphicon-user"></span>
							Upload</a></li>
					<li><a href=""><span class="glyphicon glyphicon-log-in"></span>Log
							out</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	
	<div class="row">
		<div class="col-lg-5">
			<div class="media">
				<a class="pull-left" href="#"> <img
					class="media-object dp img-circle" src="images/no-photo.jpg"
					style="width: 100px; height: 100px;">
					<button><form:form action="profile-pic-upload">Change profile photo</form:form></button>
				</a>
				<div class="media-body">
					<h4 class="media-heading">
						Welcome, ${user.username}!<small>
							<hr style="margin: 8px auto">
							<div class="col-md-7 user-details">
								<div class="row overview">
									<div class="col-md-4 user-pad text-center">
										<h3>FOLLOWERS</h3>
										<p>${followers}</p>
									</div>
									<div class="col-md-4 user-pad text-center">
										<h3>FOLLOWING</h3>
										<h4>456</h4>
									</div>
								</div>
							</div>
				</div>
			</div>
		</div>
	</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="panel-body">
				<button class="btn btn-info btn-pressure btn-sensitive">My
					Songs</button>
				<button class="btn btn-info btn-pressure btn-sensitive">Liked
					Songs</button>
				<button class="btn btn-info btn-pressure btn-sensitive">My
					playlist</button>
			</div>
		</div>
	</div>
</body>
</html>

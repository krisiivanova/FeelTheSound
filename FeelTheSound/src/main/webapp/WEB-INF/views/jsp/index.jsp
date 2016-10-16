<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>FeelTheSound</title>
<link rel="icon" href="images/tab.png">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/prettyPhoto.css" type="text/css">
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/cufon-yui.js" type="text/javascript"></script>
<script src="js/cufon-replace.js" type="text/javascript"></script>
<script type="text/javascript" src="js/Josefin_Sans_600.font.js"></script>
<script type="text/javascript" src="js/Lobster_400.font.js"></script>
<script type="text/javascript" src="js/sprites.js"></script>
<script type="text/javascript" src="js/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="js/jquery.jplayer.settings.js"></script>
<script type="text/javascript" src="js/gSlider.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
</head>
<body id="page1">
	<div id="main">
		<header>
			<nav>
				<ul>
					<li class="active"><a href="index">Home</a></li>
					<li><a href="Login">Login</a></li>
					<li><a href="Register">Register</a></li>

				</ul>
			</nav>
			<h1>
				<a href="index">FeelTheSound</a>
			</h1>
			<div class="header-slider">
				<ul>
					<li>Welcome to our new site FeelTheSound for sharing the
						passion of music!</li>
					<li>Enjoy our tracks, create your profile, personal playlists,
						upload songs and find the latest trends in music.</li>
				</ul>
			</div>
			<a href="#" class="hs-prev"><img src="images/prev.png" alt=""></a>
			<a href="#" class="hs-next"><img src="images/next.png" alt=""></a>
			<a href="Search" class="header-more">Search music...</a>
		</header>
		<div class="tumbvr">
			<div class="tumbvr-mask"></div>
			<ul>
				<li><img src="images/01.png"></li>
				<li><img src="images/02.png"></li>
				<li><img src="images/03.png"></li>
				<li><img src="images/04.png"></li>
				<li><img src="images/05.png"></li>
				<li><img src="images/06.png"></li>
				<li><img src="images/07.png"></li>
				<li><img src="images/08.png"></li>
				<li><img src="images/09.png"></li>
			</ul>
		</div>
		
		<article id="content">

				<h2 style="text-align: center;" align="center">
					Latest song added to FeelTheSound:<br>${song.artist} -
					${song.name}
				</h2>
				
				<center>
					<td><audio controls autoplay>
							<source src="./songs/${song.songPath}" type="audio/mp3" />
						</audio></td>
						</center>
			</div>

		</article>
		
		<div class="af clear"></div>
	</div>
	<footer>
		<span> FeelTheSound IT Talents Final Project <br>Authors:
			Bojidar and Kristina
		</span>
	</footer>

	<script type="text/javascript">
		Cufon.now()
		$(function() {
			$('nav,.more,.header-more').sprites()

			$('.header-slider').gSlider({
				prevBu : '.hs-prev',
				nextBu : '.hs-next'
			})
		})
		$(window).load(function() {
			$('.tumbvr')._fw({
				tumbvr : {
					duration : 2000,
					easing : 'easeOutQuart'
				}
			})
			$('a[rel=prettyPhoto]').each(function() {
				var th = $(this), pb
				th.append(pb = $('<span class="playbutt"></span>').css({
					opacity : .7
				}))
				pb.bind('mouseenter', function() {
					$(this).stop().animate({
						opacity : .9
					})
				}).bind('mouseleave', function() {
					$(this).stop().animate({
						opacity : .7
					})
				})
			}).prettyPhoto({
				theme : 'dark_square'
			})
		})
	</script>
</body>
</html>
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
<!--[if lt IE 7]> <div style=' clear: both; height: 59px; padding:0 0 0 15px; position: relative;'> <a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/images/upgrade.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." /></a></div> <![endif]-->
<!--[if lt IE 9]><script src="js/html5.js" type="text/javascript"></script><![endif]-->
<!--[if IE]><link href="css/ie_style.css" rel="stylesheet" type="text/css" /><![endif]-->
</head>
<body id="page1">
	<div id="main">
		<header>
			<nav>
				<ul>
					<li><a href="./profile">Profile</a></li>
					<li><a href=""></a></li>
					<li><a href="">Search</a></li>
				</ul>
			</nav>
			<br> <br> <br>
			<h1>
				<a href="./home">FeelTheSound</a>
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

		</header>

		<div class="tumbvr">
			<div class="tumbvr-mask"></div>
			<ul>
				<li><img src="images/01.jpg" alt=""></li>
				<li><img src="images/02.jpg" alt=""></li>
				<li><img src="images/03.jpg" alt=""></li>
				<li><img src="images/04.jpg" alt=""></li>
				<li><img src="images/05.jpg" alt=""></li>
				<li><img src="images/06.jpg" alt=""></li>
				<li><img src="images/07.jpg" alt=""></li>
				<li><img src="images/08.jpg" alt=""></li>
				<li><img src="images/09.jpg" alt=""></li>
				<li><img src="images/10.jpg" alt=""></li>
			</ul>
		</div>
		<div>
			<form action="" id="search-form">
				<fieldset>
					<input type="text" id="search" name="search" /> <input
						type="submit" id="search-submit" value="" />
				</fieldset>
			</form>
		</div>

		<article id="content">
			<center>
				<div>
					<h1 id="search">Search your favourite song:</h1>
					<form>
						<input type="text" name="search" placeholder="Search.."> <input
							type="submit" value="GO" id="goSearch">
					</form>
				</div>
				<!-- audio player begin -->
				<div id="jplayer"></div>
				<div class="jp-audio">
					<h2>New Song</h2>
					<div class="jp-type-single">
						<div id="jp_interface_1" class="jp-interface">
							<ul class="jp-controls">
								<li><a href="#" class="jp-play"></a></li>
								<li><a href="#" class="jp-pause"></a></li>
								<li><a href="#" class="jp-prev">Previous Track</a></li>
								<li><a href="#" class="jp-next">Next Track</a></li>
								<li><a href="#" class="jp-more-songs">Listen to More
										Songs</a></li>
							</ul>
							<div class="jp-progress">
								<div class="jp-seek-bar">
									<div class="jp-play-bar"></div>
								</div>
							</div>
							<div class="jp-title"></div>
						</div>
					</div>
				</div>
			</center>
			<!-- audio player end -->
			<br>
			<h2 class="pl">
				<center>My songs:</center>
			</h2>
			<table class="table-fill">
				<thead>
					<tr>
						<th class="text-left">Artist</th>
						<th class="text-left">Song name</th>
						<th class="text-left">Add to playlist</th>
					</tr>
				</thead>
				<tbody class="table-hover">

					<form method="POST">
						<tr>
							<td class="text-left"><center>Mile Kitic</center></td>
							<td class="text-left"><center>Plava ciganka</center></td>
							<td><center>
									<button class="btn btn-info btn-pressure btn-sensitive"
										id="btnAdd" a href="./addToPlaylist">Add to playlist</button>
								</center></td>
						</tr>
					</form>
				</tbody>
			</table>
			<script type="text/javascript">
				Cufon.now()
				$(function() {
					$('nav,.more,.header-more').sprites()
					$('.header-slider').gSlider({
						prevBu : '.hs-prev',
						nextBu : '.hs-next'
					})
				})
				$(window)
						.load(
								function() {
									$('.tumbvr')._fw({
										tumbvr : {
											duration : 2000,
											easing : 'easeOutQuart'
										}
									}).bind('click', function() {
										location = "index-3.html"
									})
									$('a[rel=prettyPhoto]')
											.each(
													function() {
														var th = $(this), pb
														th
																.append(pb = $(
																		'<span class="playbutt"></span>')
																		.css(
																				{
																					opacity : .7
																				}))
														pb
																.bind(
																		'mouseenter',
																		function() {
																			$(
																					this)
																					.stop()
																					.animate(
																							{
																								opacity : .9
																							})
																		})
																.bind(
																		'mouseleave',
																		function() {
																			$(
																					this)
																					.stop()
																					.animate(
																							{
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
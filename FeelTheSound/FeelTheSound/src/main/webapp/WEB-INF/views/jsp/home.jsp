<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE HTML>
<html lang="en">
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
				<li><img src="images/01.png" alt=""></li>
				<li><img src="images/02.png" alt=""></li>
				<li><img src="images/03.png" alt=""></li>
				<li><img src="images/04.png" alt=""></li>
				<li><img src="images/05.png" alt=""></li>
				<li><img src="images/06.png" alt=""></li>
				<li><img src="images/07.png" alt=""></li>
				<li><img src="images/08.png" alt=""></li>
				<li><img src="images/09.png" alt=""></li>
			</ul>
		</div>
		<article id="content">
			<center>
				<div>
					<h1 id="search" style="color:#7df442">Search your favourite songs:</h1>
					<form>
						<input type="text" id="searchInput" name="search" placeholder="Search.."> 
						 <input type="button" id="searchButton" value="Goo" onclick="searchResult()" />
					</form>
				</div>

			<br>
			
			<div id="mySearchDiv">
			
			</div>
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
<script>
	function searchResult() {
		var searchText = $("#searchInput").val();
		
		$.ajax({
			url: "./searchText",
			type:"GET",
			datatype: 'html',
			data:{
				searchText: searchText,
			},
			success: function(data){
				console.log(data);
				$("#mySearchDiv").empty();
				$("#mySearchDiv").append(data);
			}
		});
		
	}
</script>
</html>
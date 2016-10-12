<%@ page language = "java" contentType = "text/html; charset=UTF-8"
pageEncoding = "UTF-8" %> 
<%@ taglib prefix = "form" uri =
"http://www.springframework.org/tags/form" %>


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
	<h1><a href="index.jsp">FeelTheSound</a></h1>
	<div class="header-slider">
		<ul>
			<li>Welcome to our new site FeelTheSound for sharing the passion of music!</li>
			<li>Enjoy our tracks, create your profile, personal playlists, upload songs and find the latest trends in music. </li>
		</ul>
	</div>
	<a href="#" class="hs-prev"><img src="images/prev.png" alt=""></a>
	<a href="#" class="hs-next"><img src="images/next.png" alt=""></a>

</header>
<div class="innercopy">More Website Templates at TemplateMonster.com!</div>
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
  



	<div class="col-1">
		<div class="p2">
		<h2>New Album</h2>
		<img src="images/col-1-img1.png" class="p1" alt=""> <a href="#" class="more">Read More</a></div>
		<div class="p2">
		<h2>New Video</h2>
		<a href="www.youtube.com" rel="prettyPhoto"><img class="p1" src="images/col1-video-thumb1.jpg" alt=""></a>
		<div class="alc"><a href="index-2.html">More Videos</a></div>
		</div>
	</div>
	<div class="col-2">
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
				<li><a href="#" class="jp-more-songs">Listen to More Songs</a></li>
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
		<!-- audio player end -->
		<h2 class="pl">Latest songs added:</h2>
		<div class="table-border">
		<table class="dates" cellpadding="0" cellspacing="0" border="0">
			<tr class="thead">
			<td class="col1">Date / Venue</td>
			<td class="col2">Location</td>
			<td class="col3">Information</td>
			</tr>
			<tr class="even">
			<td class="col1">April 6, 2011<br>
				<a href="#" class="name">Motorpint Arena</a><br>
				<a href="#" class="facebook-event">Facebook Event</a></td>
			<td>416 S. 9th St. 83702<br>
				Boise, ID</td>
			<td>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium dolorlaudantium, totam rem aperiam.</td>
			</tr>
			<tr>
			<td class="col1">April 6, 2011<br>
				<a href="#" class="name">Echo Arena</a><br>
				<a href="#" class="facebook-event">Facebook Event</a></td>
			<td>416 S. 9th St. 83702<br>
				Boise, ID</td>
			<td>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium dolorlaudantium, totam rem aperiam.</td>
			</tr>
			<tr class="even">
			<td class="col1">April 6, 2011<br>
				<a href="#" class="name">Motorpint Arena</a><br>
				<a href="#" class="facebook-event">Facebook Event</a></td>
			<td>416 S. 9th St. 83702<br>
				Boise, ID</td>
			<td>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium dolorlaudantium, totam rem aperiam.</td>
			</tr>
			<tr>
			<td class="col1">April 6, 2011<br>
				<a href="#" class="name">The NEC Arena</a><br>
				<a href="#" class="facebook-event">Facebook Event</a></td>
			<td>416 S. 9th St. 83702<br>
				Boise, ID</td>
			<td>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium dolorlaudantium, totam rem aperiam.</td>
			</tr>
		</table>
		</div>
	</div>
  </article>
  <div class="af clear"></div>
</div>
<footer>
	<span>
    FeelTheSound IT Talents Final Project
    <br>Authors: Bojidar and Kristina
	</span>
</footer>
<script type="text/javascript">Cufon.now()
$(function(){
	$('nav,.more,.header-more').sprites()

	$('.header-slider').gSlider({
		prevBu:'.hs-prev',
		nextBu:'.hs-next'
	})
})
$(window).load(function(){
	$('.tumbvr')._fw({tumbvr:{
		duration:2000,
		easing:'easeOutQuart'
	}})
	.bind('click',function(){
		location="index-3.html"
	})
	
	$('a[rel=prettyPhoto]').each(function(){
		var th=$(this),
			pb
		th
			.append(pb=$('<span class="playbutt"></span>').css({opacity:.7}))
		pb
			.bind('mouseenter',function(){
				$(this)
					.stop()
					.animate({opacity:.9})
			})
			.bind('mouseleave',function(){
				$(this)
					.stop()
					.animate({opacity:.7})
			})
	})
	.prettyPhoto({theme:'dark_square'})
})
</script>
</body>
</html>
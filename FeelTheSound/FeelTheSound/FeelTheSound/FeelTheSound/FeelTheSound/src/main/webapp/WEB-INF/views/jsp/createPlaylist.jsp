<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FeelTheSound</title>
<link rel="stylesheet" href="css/style.css">
<link rel="icon" href="images/tab.png">
</head>
<body>
	<center>
		<img src="images/logo.png" alt="Logo" align="middle">
	</center>


	<section id="feature" class="transparent-bg">
	<div class="container">
		<div class="center wow fadeInDown">
			<h2>Create Playlist:</h2>
		</div>
		<div class="row contact-wrap">
			<form id="main-contact-form" class="contact-form" method="post"
				action="./CreatePlaylist">
				<div class="col-sm-5 col-sm-offset-1">
					<div class="form-group">
						<label>Name*</label> <input type="text" name="name"
							class="form-control" maxlength="30" required="required"
							style="border-color: #BDBDBD">
					</div>
					<div class="form-group">
						<input id="submit" value="Create Playlist" type="submit"
							name="submit" class="btn btn-primary btn-lg" required="required">
					</div>
				</div>
			</form>
		</div>
	</section>
</body>
</html>
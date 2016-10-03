<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>FeelTheSound Login</title>
		<link rel="stylesheet" href="css/style.css">
		<link rel="icon" href="images/tab.png">
	</head>
	
	<body>
		<center><img src="images/logo.png" alt="Logo" align="middle"></center>
		<div class="container">
			<section class="register">
				<h1>Login on FeelTheSound</h1>
				<form method="post" action="index.jsp">
					<div class="reg_section username">
						<h3>Username</h3>
						<input type="text" name="username" value="" placeholder="Your Desired Username">
					</div>
					<div class="reg_section password">
						<h3>Password</h3>
						<input type="password" name="password" value="" placeholder="Your Password">
					</div>
						<p class="submit"><input type="submit" name="commit" value="Sign In"></p>
						<a href="register.jsp"><font color="#19ea51">Don't have an account? Sign up.</font></a>
					</form>
				</section>
			</div>
		</body>
	</html>	
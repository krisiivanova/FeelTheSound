<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>FeelTheSound SignUp</title>
		<link rel="stylesheet" href="css/style.css">
		<link rel="icon" href="images/tab.png">
		<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	</head>
	
	<body>
		<center><img src="images/logo.png" alt="Logo" align="middle"></center>
		<div class="container">
			<section class="register">
				<h1>Sign up on FeelTheSound</h1>
				  <form:form action="register" method="post" commandName="userForm">
					<div class="reg_section username">
					<h3>Username</h3>
					<form:input path="username" />
					</div>
					<div class="reg_section email">
						<h3>Email</h3>
						<form:email path="email" />
					</div>
					<div class="reg_section password">
						<h3>Password</h3>
						<form:password path="password" />
					</div>
					<div class="reg_section password">
						<h3>Confirm password</h3>
						<form:confirm path="confirm" />
					</div>
					<p class="submit"><input type="submit" name="commit" value="Sign Up"></p>
				 </form:form>
			</section>
		</div>
	</body>
</html>	
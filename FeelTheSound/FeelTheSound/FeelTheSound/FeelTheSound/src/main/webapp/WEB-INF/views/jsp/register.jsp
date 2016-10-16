<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>FeelTheSound SignUp</title>
<link rel="stylesheet" href="css/style.css">
<link rel="icon" href="images/tab.png">
</head>

<body>
	<center>
		<img src="images/logo.png" alt="Logo" align="middle">
	</center>

	<div class="container">
		<section class="register">
			<h1>Sign up on FeelTheSound</h1>

			<form id="main-contact-form" class="contact-form" name="contact-form"
				method="post" action="./Register">
				<p style="color: red;" align="center">
					<c:set var="ErrorMessage" scope="request"
						value="${ErrorRegMessage}" />
					<c:if test="${ErrorRegMessage != null && ErrorRegMessage != ' '}">
						<font color="red"><c:out value="${ErrorRegMessage}" /></font>
					</c:if>
				</p>
				<div class="col-sm-5 col-sm-offset-1">
					<div class="form-group">
						<label>Username*</label> <input type="text" name="username"
							class="form-control" required="required">
					</div>
					<div class="form-group">
						<label>Email*</label> <input type="text" name="email"
							class="form-control" required="required">
					</div>
				</div>
				<div class="col-sm-5">
					<div class="form-group">
						<label>Password*</label> <input type="password" name="password"
							id="password" class="form-control" required="required">
					</div>
					<div class="form-group">
						<label>Repeat Password*</label> <input type="password"
							name="password2" id="password2" class="form-control">
					</div>
					<div id="msgbox"></div>
					<div class="form-group">
						<button id="submit" type="submit" name="submit"
							class="btn btn-primary btn-lg" required="required">Sign
							up</button>
							<br><br>
						<a href="./Login"><font color="#19ea51">Already have an
								account? Log in.</font></a>
					</div>
				</div>
			</form>
		</section>
	</div>
</body>
</html>

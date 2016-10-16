<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>FeelTheSound</title>
<link rel="stylesheet" href="css/style.css">
<link rel="icon" href="images/tab.png">
</head>

<body>
	<center>
		<img src="images/logo.png" alt="Logo" align="middle">
	</center>
	<div class="container">
		<section class="register">
		<h1>Create your playlist</h1>
			<form id="main-contact-form" class="contact-form" method="post"
				action="./CreatePlaylist">
				<div class="col-sm-5 col-sm-offset-1">
					<div class="form-group">
						<p style="color: red;" align="center">
							<c:set var="Message" scope="request" value="${Message}"/>
								<c:if test="${!empty Message}">
									<font color="red"><c:out value="${Message}"/></font><br></p>
								</c:if>
						</p>
					</div>
					<div class="form-group">
						<label>Playlist name</label> <input type="text" name="name"
							class="form-control" required="required" maxlength="30">
					</div>
					
						<button id="submit" type="submit" name="submit"
							class="btn btn-primary btn-lg" required="required">Create</button>
							<br><br>
					</div>
				</div>
			</form>
		</section>
	</div>
</body>
</html>
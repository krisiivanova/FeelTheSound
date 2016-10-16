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
			<h1>Update your profile information</h1>
			<form id="main-contact-form" class="contact-form" name="contact-form"
				method="post" action="./editProfile">
				
				<c:set var="Message" scope="request" value="${Message}"/>
										<c:set var="Success" value="Successfully edit profile info"/>
										<c:if test="${Message != null && Message != ' '}">
											<c:choose>
												<c:when test ="${Message eq Success}">
													<font color="green"><c:out value="${Message}"/></font>
												</c:when>
											<c:otherwise>
												<font color="red"><c:out value="${Message}"/></font>
											</c:otherwise>
											</c:choose>
										</c:if>
										
										
				<div class="col-sm-5 col-sm-offset-1">
					<div class="form-group"></div>
					<div class="form-group">
						<label>First name:</label><br>
						<c:if test="${not empty user.firstName }">
							<p>
								(
								<c:out value="${user.firstName}" />
								)
							</p>
						</c:if>
						<input type="text" name="first_name" class="form-control"
							maxlength="25" style="border-color: #BDBDBD">
					</div>
					<div class="form-group">
						<label>Last name:</label><br>
						<c:if test="${not empty user.lastName}">
							<p>
								(
								<c:out value="${user.lastName}" />
								)
							</p>
						</c:if>
						<input type="text" name="last_name" class="form-control"
							maxlength="25" style="border-color: #BDBDBD">
					</div>

					<div class="form-group">
						<label>City:</label><br>
						<c:if test="${not empty user.city }">
							<p>
								(
								<c:out value="${user.city}" />
								)
							</p>
						</c:if>
						<input type="text" name="city" maxlength="25" class="form-control"
							style="border-color: #BDBDBD">
					</div>
					<div class="form-group">
						<label>Country:</label><br>
						<c:if test="${not empty user.country}">
							<p>
								(
								<c:out value="${user.country}" />
								)
							</p>
						</c:if>
						<input type="text" name="country" maxlength="25"
							class="form-control" style="border-color: #BDBDBD">
					</div>

					<div class="form-group">
						<br>
						<button id="submit" type="submit" name="submit"
							class="btn btn-primary btn-lg" required="required">Edit</button>
					</div>
			</form>
										
		</section>
	</div>
</body>
</html>
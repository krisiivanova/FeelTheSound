<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload your profile picture</title>
<script type="text/javascript">
	function Validatebodypanelbumper(theForm) {
		var regexp;
		var extension = new FormData(theForm).get("file").value
				.lastIndexOf('.');
		if ((extension.toLowerCase() != ".gif")
				&& (extension.toLowerCase() != ".jpg") && (extension != "")) {
			alert("The \"FileUpload\" field contains an unapproved filename.");
			theForm.file.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<style>
#btn {
	width: 110px;
	height: 20px;
}
</style>
<body style="background-color: #ADFF2F;">
	<img src="images/feelTheSound.jpg" align="right" width=400px;
		height=200px;>

	<div>
		<form method="POST" enctype="multipart/form-data"
			onsubmit="Validatebodypanelbumper()">
			<table>
				<tr>
					<td><h2>Choose your profile picture: </h2></td>
				<tr>
					<br>
				<tr></tr>
				<tr>
					<td><input type="file" id="btn" name="file" accept="image/*" /></td>
					<td></td>
					<td><input type="submit" id="btn" value="Upload" />
					<a href = "./profile"/></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
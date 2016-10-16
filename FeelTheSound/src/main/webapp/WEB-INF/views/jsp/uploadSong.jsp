<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FeelTheSound SignUp</title>
<link rel="icon" href="images/tab.png">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>

<body>
	<center>
		<img src="images/logo.png" alt="Logo" align="middle">
	</center>

	<div align="center">
		<form method="POST" enctype="multipart/form-data"
			onsubmit="Validatebodypanelbumper()" action="./uploadMusic">
			<table align="center">
				<tr>
					<td><h2 style="color: black">Upload your own song:</h2></td>
				</tr>


				<tr>
					<td><input type="file" id="btn1" name="file" accept="mp3/*"
						required="required" onchange = "e"/></td>
					<td></td>
					<td><input type="submit" name="submit" id="btn" value="Upload"
						required="required" /></td>
				</tr>

				<tr>
					<td><input name="artistName" type="text" placeholder="Artist"
						required="required" id="tableInput" /></td>
				</tr>
				<tr>
					<td><input name="songName" placeholder="Song"
						required="required" type="text" id="tableInput" /></td>
				</tr>
				<tr>
					<td><input name="janr" placeholder="Genre" type="text"
						required="required" id="tableInput" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script>
var file = document.getElementById('btn1');

file.onchange = function(e) {
	var ext = this.value.match(/\.([^\.]+)$/)[1];
	switch (ext) {
	case 'mp3':		
		break;
	default:
		alert('Not allowed to add this type of file');
		this.value = '';
	}
};
</script>
</html>
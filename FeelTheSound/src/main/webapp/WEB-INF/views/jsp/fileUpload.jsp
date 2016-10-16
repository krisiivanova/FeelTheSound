<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="images/tab.png">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<title>Upload your profile picture</title>
</head>

<body>
	<center>
		<div>
			<center>
				<img src="images/logo.png" alt="Logo" align="middle">
				<form method="POST" enctype="multipart/form-data"
					onsubmit="Validatebodypanelbumper()">
			</center>
			<div>
				<table>
					<tr>
						<td><h2 style="color: black">&nbsp; &nbsp; &nbsp;
								&nbsp;Choose your profile picture:</h2></td>
					<tr>
						<br>
					<tr></tr>
					<tr>
						<td><input type="file" id="btn1" name="file" accept="image/*" /></td>
						<td></td>
						<td><input type="submit" id="btn" value="Upload" /></td>
					</tr>
				</table>
			</div>
			</form>
	</center>
	</div>
</body>

<script>
	var file = document.getElementById('btn1');

	file.onchange = function(e) {
		var ext = this.value.match(/\.([^\.]+)$/)[1];
		switch (ext) {
		case 'jpg':
		case 'bmp':
		case 'png':
		case 'tif':
		case 'jpeg':
			
			break;
		default:
			alert('Not allowed to add this type of file');
			this.value = '';
		}
	};
</script>

</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="css/table.css">
<div id="songsList">
	<table class="table-fill">
		<thead>
			<tr>
				<th class="text-left">Artist Name</th>
				<th class="text-left">Song Name</th>
				<th class="text-left">Genre</th>
				<th class="text-left">Play</th>
			</tr>
		</thead>
		<tbody class="table-hover">
			<c:forEach items="${songs}" var="song">
				<tr>
					<td><center>${song.artist}</center></td>
					<td><center>${song.name}</center></td>
					<td><center>${song.genre}</center></td>
					<td><audio controls="controls">

							<source src="./songs/${song.songPath}" type="audio/mp3" />
						</audio></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
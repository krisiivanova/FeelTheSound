<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table-fill">
	<thead>
		<tr>
			<th class="text-left">Artist</th>
			<th class="text-left">Song name</th>
			<th class="text-left">Add to playlist</th>
			<th class="text-left">Play</th>
		</tr>
	</thead>
	<tbody class="table-hover">
		<c:forEach items="${songs}" var="song">
			<tr>
				<td class="text-left"><center>${song.artist}</center></td>
				<td class="text-left"><center>${song.name}</center></td>
				<td><center>
						<!-- <input type="button" id="addToPlaylistButton" value="Add to playlist" onclick="addToPlayList(song.id)" />  -->
						<button class="btn btn-info btn-pressure btn-sensitive"
							id="btnAdd" a href="./addToPlaylist">Add to playlist</button>
					</center></td>
				<td>
					<center>
						<audio controls="controls">
							<source src="./songs/${song.songPath}" type="audio/mp3" />
						</audio>
					</center>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
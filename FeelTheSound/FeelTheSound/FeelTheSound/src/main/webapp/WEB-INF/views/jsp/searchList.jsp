<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table-fill">
	<thead>
		<tr>
			<th class="text-left">Artist</th>
			<th class="text-left">Song name</th>
			<th class="text-left">Add to playlist</th>
			<th class="text-left">Play</th>
			<th class="text-left">Like</th>
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
				<td>
					<center>
						<button class="like-button" id="btnAdd" a href="./like" onclick="getSongs(${user.id})">Like</button>
					</center>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>	
	
	$(function(songId) {
		
		$('.like-button').click(function() {
			var obj = $(this);
			if (obj.data('liked')) {
				obj.data('liked', false);
				obj.html('Like');
			} else {
				obj.data('liked', true);
				obj.html('Unlike');
			}
		});

		$.ajax({
			url : "./like",
			type : "GET",
			datatype : 'html',
			data : {
				songId : songId,
			}
		});
	});
	
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table-fill">
	<thead>
		<tr>
			<th class="text-left">Artist</th>
			<th class="text-left">Song name</th>
			<th class="text-left">Genre</th>
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
				<td class="text-left"><center>${song.genre}</center></td>
				<td>
					<center>
							<div class="dropdown">
								<select name='playlist'>
									<option class='head'>Add to playlist</option>
									<div id="myPlaylistsDiv">
										<c:forEach items="${playlists}" var="playlist">
											<option value="${playlist.name}">${playlist.name}</option>
										</c:forEach>
									</div>
								</select>



							<button class="add-button" id="addIt" a
								href="./AddSongToPlaylist"
								onclick="addToPlaylist(${song.id},${user.id},${playlist.id})">Add</button>



						</div>
					</center>
					</td>
				<td>
					<center>
						<audio controls="controls">
							<source src="./songs/${song.songPath}" type="audio/mp3" />
						</audio>
					</center>
				</td>
				<td>
					<center>
						<button class="like-button" id="likeIt" a href="./like"
							onclick="getSongs(${user.id}, ${song.id})">Like</button>
					</center>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>
function addToPlaylist(songId, userId, playlistId) {
	var add = $("#addIt").val();
	console.log("User : "+userId);
	console.log("song : "+songId);
	console.long("playlist: " + playlistId);
	$.ajax({
		url: "./AddSongToPlaylist",
		type:"GET",
		datatype: 'html',
		data:{
			songId: songId,
			userId: userId,
			playlistId: playlistId,
		},
		success: function(data){
			console.log(data);
			$("#mySearchDiv").empty();
			$("#mySearchDiv").append(data);
		}
	});
}	

function getSongs(userId, songId) {
	var like = $("#likeIt").val();
	console.log("User : "+userId);
	console.log("song : "+songId);
	$.ajax({
		
		url: "./like",
		type:"GET",
		datatype: 'html',
		data:{
			songId: songId,
			userId: userId,
		},
		success: function(data){
			console.log(data);
			$("#mySearchDiv").empty();
			$("#mySearchDiv").append(data);
		}
	});
}

</script>

<!-- <script>	
	
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
	
</script> -->
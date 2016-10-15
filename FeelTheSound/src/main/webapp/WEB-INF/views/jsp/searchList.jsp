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
							<select name='playlistSelect' onchange="setPlaylistId()">
								<option class='head'>Add to playlist</option>
								<c:forEach items="${playlists}" var="playlist">
									<div id="myPlaylistsDiv">
										<option value="${playlist.id}">${playlist.name}</option>
									</div>
								</c:forEach>
							</select>
						</div>
						<input type="hidden" id="playlistId" value="">
						<button class="add-button" id="addIt" a href="./AddSongToPlaylist"
								onclick="addToPlaylist(${song.id},${user.id})">Add
						</button>
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
						<input type="button" value="Like" class="like-button" id="likeIt${song.id}" a href="./like"
							onclick="getSongs(${user.id}, ${song.id})"></button>
					</center>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>

function setPlaylistId(){
	var playlistId = event.target.value;
	$('#playlistId').val(playlistId);
	console.log(event.target.value);
}

function addToPlaylist(songId, userId) {
	var add = $("#addIt").val();
	var playId = $('#playlistId').val();
	console.log("Id "+ playId);
	console.log("User : "+userId);
	console.log("song : "+songId);
	$.ajax({
		url: "./AddSongToPlaylist",
		type:"GET",
		datatype: 'html',
		data:{
			songId: songId,
			userId: userId,
			playlistId: playId
		},
		success: function(data){
			console.log(data);
			$("#mySearchDiv").empty();
			$("#mySearchDiv").append(data);
		}
	});
}	
function getSongs(userId, songId) {
	var like = $("#likeIt"+songId).val();
	
	if(like === 'Like')
		$("#likeIt"+songId).val('Unlike');
	if(like === 'Unlike')
		$("#likeIt"+songId).val('Like');
	
	console.log("Like value : "+like);
	$.ajax({
		url: "./like",
		type:"GET",
		datatype: 'html',
		data:{
			songId: songId,
			userId: userId,
			like: like,
		},
		success: function(data){
			console.log(data);
			$("#mySearchDiv").empty();
			$("#mySearchDiv").append(data);
		}
	});
}
</script>


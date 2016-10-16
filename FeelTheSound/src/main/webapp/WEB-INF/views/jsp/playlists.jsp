<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="css/table.css">
<div id="songsList">
	<table class="table-fill">
		<thead>
			<tr>
				<th class="text-left">Playlist name</th>
				<th class="text-left">Delete playlist</th>
			</tr>
		</thead>
		<tbody class="table-hover">
			<c:forEach items="${playlists}" var="playlist">
				<tr>
					<td><center>
							<button class="btn btn-info btn-pressure btn-sensitive"
								style="background-color: black"
								onclick="getMyPlaylist(${playlist.id})">${playlist.name}</button>
						</center></td>

					<td><center>
							<button class="btn btn-info btn-pressure btn-sensitive"
								style="background-color: black"
								onclick="deleteMyPlaylist(${playlist.id})">Delete</button>
						</center></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="selectPlaylistResult"></div>
<script>
function getMyPlaylist(playlistId){
	$.ajax({
		url: "./MyPlaylist",
		type:"GET",
		datatype: 'html',
		data:{
			playlistId: playlistId,
		},
		success: function(data){
			$("#songsList").empty();
			$("#selectPlaylistResult").empty();
			$("#selectPlaylistResult").append(data);
		}
	});
}

function deleteMyPlaylist(playlistId){
	$.ajax({
		url: "./DeletePlaylist",
		type:"POST",
		datatype: 'html',
		data:{
			playlistId: playlistId,
		},
		success: function(data){
			$("#songsList").empty();
			$("#selectPlaylistResult").empty();
			$("#selectPlaylistResult").append(data);
		}
	});
}
</script>
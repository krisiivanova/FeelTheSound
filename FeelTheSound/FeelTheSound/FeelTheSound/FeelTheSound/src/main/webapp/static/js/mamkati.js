/**
 * 
 */
document.addEventListener('DOMContentLoaded', function(){
	function addToPlaylist(songId, userId, playlistId) {
		var add = $("#addIt").val();
		console.log("User : "+userId);
		console.log("song : "+songId);
		console.log("playlist: " + playlistId);
		$.ajax({
			url: "./AddSongToPlaylist",
			type:"POST",
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
})

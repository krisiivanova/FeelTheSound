<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="songsList">
	<table class="table-fill">
		<thead>
			<tr>
				<th class="text-left">Artist Name</th>
				<th class="text-left">Song Name</th>
			</tr>
		</thead>
		<tbody class="table-hover">
			<c:forEach items="${songs}" var="song">
				<tr>
					<td>${song.artist}</td>
					<td>${song.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
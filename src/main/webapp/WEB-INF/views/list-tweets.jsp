<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<table class="table table-striped">
		<caption>Your tweets</caption>
		<thead>
			<tr>
				<th>Content</th>
				<th>Date</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tweets}" var="tweet">
				<tr>
					<td>${tweet.content}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${tweet.targetDate}" /></td>
					<td> 
						<a type="button"
							class="btn btn-warning" href="/delete-tweet?id=${tweet.id}">Delete
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a type="button" class="btn btn-success" href="/add-tweet">Add</a>
	</div>
</div>
<%@ include file="common/footer.jspf"%>
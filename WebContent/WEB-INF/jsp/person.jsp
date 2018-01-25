<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Personne</title>
<c:url var="view" value="/actions/directory/group/view/1" />
<c:url var="edit" value="/actions/directory/person/edit" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">

		<div>
			<c:if test="${type_notify == 'success'}">
				<div class="alert alert-<c:out value="${type_notify}"></c:out>">
					Recherche réussit</div>
			</c:if>
		</div>

		<h1>
			Informations de :
			<c:out value="${person.lastName}"></c:out>
		</h1>
		<p>Voici les informations personnelles.</p>

		<table class="table table-condensed">
			<tbody>
				<tr>
					<th scope="row">Id</th>
					<td><c:out value="${person.id}" /></td>
				</tr>
				<tr>
					<th scope="row">Last Name</th>
					<td><c:out value="${person.lastName}" /></td>
				</tr>
				<tr>
					<th scope="row">First Name</th>
					<td><c:out value="${person.firstName}" /></td>
				</tr>
				<c:if test="${user.name !='No User' && not empty user.name}">
					<tr>
						<th scope="row">Email</th>
						<td><c:out value="${person.email}" /></td>
					</tr>
					<tr>
						<th scope="row">Birthday</th>
						<td><c:out value="${person.birthDate}" /></a></td>
					</tr>
				</c:if>
				<tr>
					<th scope="row">Web Site</th>
					<td><c:out value="${person.webSite}" /></td>
				</tr>
				<tr>
					<th scope="row">Id Group</th>
					<td><a href="${view}?id=${person.groupId}"> <c:out
								value="${group}" /></a></td>
				</tr>
				<tr>
					<th></th>
					<td><c:if
							test="${user.name == person.lastName && user.id == person.id}">
							<a class="btn btn-info" href="${edit}?id=${person.id}">
								modification </a>
						</c:if></td>
				</tr>


			</tbody>
		</table>
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>

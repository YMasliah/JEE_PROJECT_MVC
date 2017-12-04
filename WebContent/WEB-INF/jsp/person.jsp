<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Personne</title>
<c:url var="edit" value="/actions/directory/person/edit" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">
		<h1>
			informations de
			<c:out value="${person.lastName}"></c:out>
			, faut rajouter des if et un lien de l'id du groupe
		</h1>
		<table class="table table-hover">
			<tr>
				<td>Id = <c:out value="${person.id}" />
				</td>
			</tr>
			<tr>
				<td>LastName = <c:out value="${person.lastName}" />
				</td>
			</tr>
			<tr>
				<td>FirstName = <c:out value="${person.firstName}" />
				</td>
			</tr>
			<tr>
				<td>WebSite = <c:out value="${person.webSite}" />
				</td>
			</tr>
			<tr>
				<td>Group = <c:out value="${group}" />
				</td>
			</tr>
		</table>
		<c:if test="${user.name == person.lastName && user.id == person.id}">
			<td><a class="btn btn-info" href="${edit}?id=${person.id}">
					modification </a></td>
		</c:if>
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>
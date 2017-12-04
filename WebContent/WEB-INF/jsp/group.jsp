<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Groupe</title>
<c:url var="view" value="/actions/directory/person/view" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">
		<h2>
			Groupe :
			<c:out value="${group.name}"></c:out>
		</h2>
		<p>
			group : bon ici. si l'user est identique a l'etudiant on peut
			modifier <br> sinon on peu juste afficher les valeurs des
			etudiants (4 valeurs) <br> et pour finir on peu rajouter un
			nouvel utilisateur
		</p>
		<table class="table table-condensed">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${persons}" var="persona">
					<tr class='clickable-row' data-href='${view}?id=${persona.id}'>
					<td><c:out value="${persona.id}" /></td>
						<td><c:out value="${persona.lastName}" /></td>
					</tr>
				
				</c:forEach>
			</tbody>
		</table>
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>
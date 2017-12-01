<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Liste de Groupes</title>
<c:url var="view" value="/actions/directory/person/view" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">
		<h1>
			<c:out value="${group.name}"></c:out>
			group : bon ici. si l'user est identique a l'etudiant on peut
			modifier <br> sinon on peu juste afficher les valeurs des
			etudiants (4 valeurs) <br> et pour finir on peu rajouter un
			nouvel utilisateur
		</h1>
		<table class="table table-hover">
			<c:forEach items="${persons}" var="persona">
				<tr>
					<td><a href="${view}?id=${persona.id}"> <c:out
								value="${persona.lastName}" />
					</a></td>
				</tr>
			</c:forEach>
		</table>
		<!--         <p> -->
		<%--             <a class="btn btn-info" href="${edit}">add new person</a> --%>
		<!--         </p> -->
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>
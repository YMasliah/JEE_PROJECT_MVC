<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Groupe</title>
<c:url var="viewPerson" value="/actions/directory/person/view" />
<c:url var="viewGroup" value="/actions/directory/group/view" />
<c:url var="edit" value="/actions/directory/person/edit" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">
<!-- 		<div> -->
<%-- 			<c:if test="${type_notify == 'success'}"> --%>
<%-- 				<div class="alert alert-<c:out value="${type_notify}"></c:out>"> --%>
<%-- 					<c:out value="${notify}"></c:out> --%>
<!-- 				</div> -->
<%-- 			</c:if> --%>
<!-- 		</div> -->

		<c:choose>
			<c:when test="${persons.size() == 0 }">
				<h1>OUPS ! Aucune peronne dans ce groupe.</h1>
			</c:when>
			<c:otherwise>
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
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${persons}" var="persona">
							<tr>
								<td><c:out value="${persona.id}" /></td>
								<td><c:out value="${persona.lastName}" /></td>
								<td><a href="${viewPerson}?id=${persona.id}"
									class="btn btn-info" role="button">Voir</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>

				<ul class="pager">
					<c:choose>
						<c:when test="${page >= 2}">
							<li><a href="${viewGroup}/${page-1}?id=${group.id}">Previous</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a>Previous</a></li>
						</c:otherwise>
					</c:choose>
					<li>Page <c:out value="${page}"></c:out></li>
					<c:choose>
						<c:when test="${persons.size() == 50}">
							<li><a href="${viewGroup}/${page+1}?id=${group.id}">Next</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a>Next</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</c:otherwise>
		</c:choose>
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Liste des groupes</title>
<c:url var="edit" value="/actions/directory/group/edit" />
<c:url var="view" value="/actions/directory/group/view" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>


	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">
		<h1>Liste des groupes dans l'annuaire</h1>
		<p>
			Voici tous les groupes de l'annuaire.
		</p>
		<table class="table table-condensed">
			<thead>
				<tr>
					<th>Id</th>
					<th>Groupe Name</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${groups}" var="groupList">
					<tr>
						<td><c:out value="${groupList.id}" /></td>
						<td><c:out value="${groupList.name}" /></td>
						<td><a href="${view}/1?id=${groupList.id}"
							class="btn btn-info" role="button">Voir</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<ul class="pager">
			<c:choose>
				<c:when test="${page >= 2}">
					<li><a href="${page-1}">Précédent</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a>Précédent</a></li>
				</c:otherwise>
			</c:choose>
			&emsp;&emsp;<li>Page <c:out value="${page}"></c:out></li>&emsp;&emsp;
			<c:choose>
				<c:when test="${groups.size() == 50}">
					<li><a href="${page+1}">Suivant</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a>Suivant</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>
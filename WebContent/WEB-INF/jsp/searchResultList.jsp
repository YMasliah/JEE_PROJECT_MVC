<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Liste des groupes</title>
<c:url var="search" value="/actions/directory/search" />
<c:url var="edit" value="/actions/directory/group/edit" />
<c:url var="view" value="/actions/directory/group/view" />
<c:url var="editPerson" value="/actions/directory/person/edit" />
<c:url var="viewPerson" value="/actions/directory/person/view" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">

		<div>
			<c:if test="${type_notify == 'success'}">
				<div class="alert alert-<c:out value="${type_notify}"></c:out>">
					<c:out value="${notify}"></c:out>
				</div>
			</c:if>
		</div>

		<h1>resultat de la recherche</h1>

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
					<li><c:if test="${key == ''}">
							<a href="${search}/${page-1}/${type}">Précédent</a>
						</c:if> <c:if test="${key != ''}">
							<a href="${search}/${page-1}/${key}/${type}">Précédent</a>
						</c:if></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a>Précédent</a></li>
				</c:otherwise>
			</c:choose>
			&emsp;&emsp;<li>Page<c:out value="${page}"></c:out></li>&emsp;&emsp;
			<c:choose>
				<c:when test="${groups.size() == 50}">
					<li><c:if test="${key == ''}">
							<a href="${search}/${page+1}/${type}">Suivant</a>
						</c:if> <c:if test="${key != ''}">
							<a href="${search}/${page+1}/${key}/${type}">Suivant</a>
						</c:if></li>
				</c:when>
				<c:when test="${persons.size() == 50}">
					<li><c:if test="${key == ''}">
							<a href="${search}/${page+1}/${type}">Suivant</a>
						</c:if> <c:if test="${key != ''}">
							<a href="${search}/${page+1}/${key}/${type}">Suivant</a>
						</c:if></li>
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
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Liste des groupes</title>
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
${flashAttr}

		<div>
			<c:if test="${type_notify == 'success'}">
				<div class="alert alert-<c:out value="${type_notify}"></c:out>">
					<c:out value="${notify}"></c:out>
				</div>
			</c:if>
		</div>

		<%-- 		<%=request.getParameter("key")%> --%>
		<%-- 		<%=request.getParameter("type")%> --%>

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
<%-- 				<c:forEach items="${persons}" var="persona"> --%>
<!-- 					<tr> -->
<%-- 						<td><c:out value="${persona.id}" /></td> --%>
<%-- 						<td><c:out value="${persona.lastName}" /></td> --%>
<%-- 						<td><a href="${viewPerson}?id=${persona.id}" --%>
<!-- 							class="btn btn-info" role="button">Voir</a></td> -->
<!-- 					</tr> -->

<%-- 				</c:forEach> --%>
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
					<li><a href="${groupList}/${page-1}">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a>Previous</a></li>
				</c:otherwise>
			</c:choose>
			<li>Page <c:out value="${page}"></c:out></li>
			<c:choose>
				<c:when test="${groups.size() == 50}">
					<li><a href="/actions/directory/group/list/${page+1}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a>Next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>
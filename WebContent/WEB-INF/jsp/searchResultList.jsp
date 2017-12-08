<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Liste des groupes</title>
<c:url var="edit" value="/actions/directory/group/edit" />
<c:url var="view" value="/actions/directory/group/view/1" />
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

		<%=request.getParameter("key")%>
		<%=request.getParameter("type")%>
		<h1>resultat de la recherche</h1>
		<table class="table table-hover">
			<c:forEach items="${persons}" var="personList">
				<tr>
					<td><a href="${viewPerson}?id=${personList.id}"> <c:out
								value="${personList.id}" />
					</a></td>
					<td><a href="${viewPerson}?id=${personList.id}"> <c:out
								value="${personList.lastName}" />
					</a></td>
				</tr>
			</c:forEach>
		</table>

		<table class="table table-hover">
			<c:forEach items="${groups}" var="groupList">
				<tr>
					<td><a href="${view}?id=${groupList.id}"> <c:out
								value="${groupList.id}" />
					</a></td>
					<td><a href="${view}?id=${groupList.id}"> <c:out
								value="${groupList.name}" />
					</a></td>
				</tr>
			</c:forEach>
		</table>
		<ul class="pager">
			<c:choose>
				<c:when test="${page >= 2}">
					<li><a href="#">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a>Previous</a></li>
				</c:otherwise>
			</c:choose>
			<li>Page <c:out value="${page}"></c:out></li>
			<c:choose>
				<c:when test="${persons.size() == 50}">
					<li><a href="#">Next</a></li>
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
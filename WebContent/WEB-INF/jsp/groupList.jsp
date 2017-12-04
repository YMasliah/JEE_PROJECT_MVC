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
			<h2>Liste des groupes</h2>
			<p>
				bug de modification si un groupe a le meme id qu'un nouveau groupe.<br>
				surement resolu lors de l'implementation de la base de donn�e
			</p>
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>Id</th>
						<th>Groupe Name</th>
					</tr>
				</thead>
				<tbody>
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
				</tbody>
			</table>
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>
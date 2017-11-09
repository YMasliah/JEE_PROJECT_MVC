<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
</head>

<body>

	<div class="container">
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
				<td>Group = <c:out value="${person.groupId}" />
				</td>
			</tr>
		</table>

	</div>

</body>
</html>
<%-- <%@ include file="/WEB-INF/jsp/include.jsp"%> --%>

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
			Edit
			<c:out value="${group.name}"></c:out>
			group : je n'aime pas le code avec les 2 c:if, a changer<br> il
			y as aussi le retour d'erreur de type a changer, en se moment sa sort
			une page d'echec
		</h1>

		<form:form method="POST" commandName="group">

			<form:errors path="*" cssClass="alert alert-danger" element="div" />
			<c:if test="${empty group.id}">
				<div class="form-group">
					<label for="id">Id :</label>
					<form:input class="form-control" path="id" />
					<form:errors path="id" cssClass="alert alert-warning" element="div" />
				</div>
			</c:if>
			<c:if test="${not empty group.id}">
				<div class="form-group">
					<label for="id">Id :</label>
					<form:input class="form-control" path="id" disabled="true" />
					<form:errors path="id" cssClass="alert alert-warning" element="div" />
				</div>
			</c:if>
			<div class="form-group">
				<label for="name">Name:</label>
				<form:input class="form-control" path="name" />
				<form:errors path="name" cssClass="alert alert-warning"
					element="div" />
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-info">Submit</button>
			</div>
		</form:form>
	</section>

	<c:import url="/WEB-INF/jsp/footer.jsp">
		<c:param name="page" value="1" />
	</c:import>

</body>
</html>
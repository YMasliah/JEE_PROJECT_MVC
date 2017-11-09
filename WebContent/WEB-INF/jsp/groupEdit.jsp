<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
</head>

<body>

	<div class="container">
		<h1>Edit <c:out value="${group.name}"></c:out> group  : je n'aime pas le code avec les 2 c:if, a changer<br> il y as aussi le retour d'erreur de type a changer, en se moment sa sort une page d'echec</h1>

		<form:form method="POST" commandName="group">

			<form:errors path="*" cssClass="alert alert-danger" element="div" />
			<c:if test="${empty group.id}">
			<div class="form-group">
				<label for="id">Id :</label>
				<form:input class="form-control" path="id"/>
				<form:errors path="id" cssClass="alert alert-warning"
					element="div" />
			</div>
			</c:if>
			<c:if test="${not empty group.id}">
			<div class="form-group">
				<label for="id">Id :</label>
				<form:input class="form-control" path="id" disabled="true"/>
				<form:errors path="id" cssClass="alert alert-warning"
					element="div" />
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
	</div>

</body>
</html>
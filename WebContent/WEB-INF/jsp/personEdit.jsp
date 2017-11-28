<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
</head>

<body>

	<div class="container">
		<h1>
			Edit
			<c:out value="${person.lastName}"></c:out>
			, il faut rajouter une verification metier sur l'id et surement
			d'autre truc tel que recuperer le nom du groupe
		</h1>
		<br> injecter l'id du groupe aussi si on crée une nouvelle
		personne a partir d'un groupe
		<form:form method="POST" commandName="person">

			<form:errors path="*" cssClass="alert alert-danger" element="div" />

			<c:if test="${empty person.id}">
				<div class="form-group">
					<label for="id">Id :</label>
					<form:input class="form-control" path="id" type="number" />
					<form:errors path="id" cssClass="alert alert-warning" element="div" />
				</div>
			</c:if>
			<c:if test="${not empty person.id}">
				<div class="form-group">
					<label for="id">Id :</label>
					<form:input class="form-control" path="id" disabled="true" />
					<form:errors path="id" cssClass="alert alert-warning" element="div" />
				</div>
			</c:if>
			<div class="form-group">
				<label for="lastName">LastName:</label>
				<form:input class="form-control" path="lastName" />
				<form:errors path="lastName" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="firstName">FirstName:</label>
				<form:input class="form-control" path="firstName" />
				<form:errors path="firstName" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="email">Mail:</label>
				<form:input path="email" class="form-control" type="email" />
				<form:errors path="email" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="webSite">WebSite:</label>
				<form:input path="webSite" class="form-control" type="url" />
				<form:errors path="webSite" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="birthDate">BirthDate:</label>
				<form:input path="birthDate" class="form-control" type="date"
					pattern="dd/MM/yyyy" />
				<form:errors path="birthDate" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<form:input path="password" class="form-control" type="password" />
				<form:errors path="password" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="groupId">groupId:</label>
				<form:input path="groupId" class="form-control" type="number" />
				<form:errors path="groupId" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-info">Submit</button>
			</div>
		</form:form>
	</div>

</body>
</html>
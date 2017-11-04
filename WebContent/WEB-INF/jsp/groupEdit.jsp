<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
</head>

<body>

	<div class="container">
		<h1>Edit <c:out value="${group.name}"></c:out> group</h1>

		<form:form method="POST" commandName="group">

			<form:errors path="*" cssClass="alert alert-danger" element="div" />
			<div class="form-group">
				<label for="id">Id :</label>
				<form:input class="form-control" path="id" rows="4" />
				<form:errors path="id" cssClass="alert alert-warning"
					element="div" />
			</div>
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
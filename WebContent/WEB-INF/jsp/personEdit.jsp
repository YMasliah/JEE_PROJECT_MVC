<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
</head>

<body>

	<div class="container">
		<h1>Edit Product</h1>

		<form:form method="POST" commandName="person">

			<form:errors path="*" cssClass="alert alert-danger" element="div" />

			<div class="form-group">
				<label for="lastName">lastName:</label>
				<form:input class="form-control" path="lastName" />
				<form:errors path="lastName" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="firstName">firstName:</label>
				<form:input class="form-control" path="firstName" rows="4" />
				<form:errors path="firstName" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="webSite">webSite:</label>
				<form:input path="webSite" class="form-control" />
				<form:errors path="webSite" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="groupId">groupId:</label>
				<form:input path="groupId" class="form-control" />
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
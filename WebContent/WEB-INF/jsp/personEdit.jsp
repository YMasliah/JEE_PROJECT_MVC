<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>Personne</title>
</head>
<body>

	<c:import url="/WEB-INF/jsp/loginMenu.jsp">
		<c:param name="page" value="1" />
	</c:import>

	<section style="float: left; width: 60%; margin: 0 1.5% 24px 1.5%;">

		<h1>
			Modification :
			<c:out value="${person.lastName}"></c:out>
		</h1>
		<p>Dans cette page, vous pouvez modifiez vos informations personnelles.</p>
		<form:form method="POST" commandName="person">

			<form:errors path="*" cssClass="alert alert-danger" element="div" />

			<c:if test="${empty person.id}">
				<div class="form-group">
					<label for="id">Id :</label>
					<form:input class="form-control" path="id" type="number" />
					<%-- 					<form:errors path="id" cssClass="alert alert-warning" element="div" /> --%>
				</div>
			</c:if>
			<c:if test="${not empty person.id}">
				<div class="form-group">
					<label for="id">Id :</label>
					<form:input class="form-control" path="id" disabled="true" />
					<%-- 					<form:errors path="id" cssClass="alert alert-warning" element="div" /> --%>
				</div>
			</c:if>
			<div class="form-group">
				<label for="lastName">LastName:</label>
				<form:input class="form-control" path="lastName" />
				<%-- 				<form:errors path="lastName" cssClass="alert alert-warning" --%>
				<%-- 					element="div" /> --%>
			</div>
			<div class="form-group">
				<label for="firstName">FirstName:</label>
				<form:input class="form-control" path="firstName" />
				<%-- 				<form:errors path="firstName" cssClass="alert alert-warning" --%>
				<%-- 					element="div" /> --%>
			</div>
			<div class="form-group">
				<label for="email">Mail:</label>
				<form:input path="email" class="form-control" type="email" />
				<%-- 				<form:errors path="email" cssClass="alert alert-warning" --%>
				<%-- 					element="div" /> --%>
			</div>
			<div class="form-group">
				<label for="webSite">WebSite:</label>
				<form:input path="webSite" class="form-control" type="url" />
				<%-- 				<form:errors path="webSite" cssClass="alert alert-warning" --%>
				<%-- 					element="div" /> --%>
			</div>
			<div class="form-group">
				<label for="birthDate">BirthDate:</label>
				<form:input path="birthDate" class="form-control" type="date"
					pattern="dd/MM/yyyy" />
				<%-- 				<form:errors path="birthDate" cssClass="alert alert-warning" --%>
				<%-- 					element="div" /> --%>
			</div>
			<div class="form-group">
				<label for="password">groupId:</label> <input name="group"
					class="form-control" value='<c:out value="${group}"></c:out>' />
			</div>
			<!-- 			<div class="form-group"> -->
			<!-- 				<label for="groupId">groupId:</label> -->
			<%-- 				<form:input path="groupId" class="form-control" type="number" /> --%>
			<%-- 				<form:errors path="groupId" cssClass="alert alert-warning" --%>
			<%-- 					element="div" /> --%>
			<!-- 			</div> -->
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
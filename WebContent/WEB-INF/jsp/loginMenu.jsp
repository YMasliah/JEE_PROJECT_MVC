<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="login" value="/actions/directory/login" />
<c:url var="logout" value="/actions/directory/logout" />
<%-- <c:url var="search" value="/actions/directory/search" /> --%>
<c:url var="passwordLost" value="/actions/directory/passwordLost" />


<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${login}">Acceuil</a>
		</div>

		<ul class="nav navbar-nav navbar-right">

			<c:choose>
				<c:when test="${empty user.name}">
					<li><a href="${login}"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</c:when>
				<c:when test="${user.name =='No User'}">
					<li><form:form method="POST" commandName="user"
							class="navbar-form navbar-right" action="${login}">
							<form:errors path="*" cssClass="alert alert-danger" element="div" />
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input class="form-control" path="id" type="number"
									value="" placeholder="Id" />
								<%-- 				<form:errors path="id" cssClass="alert alert-warning" element="div" /> --%>
							</div>
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span>
								<form:input class="form-control" path="password" type="password"
									value="" placeholder="Password" />
								<%-- 			   <form:errors path="password" cssClass="alert alert-warning" element="div" /> --%>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
							<a href="${passwordLost}">mot de passe perdu ?</a>
						</form:form></li>
				</c:when>
				<c:otherwise>
					<li> 
					Salut, <c:out value="${user.name}" />
					<a href="${logout}"><span class="glyphicon glyphicon-user"></span> Logout</a>
					</li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
</nav>

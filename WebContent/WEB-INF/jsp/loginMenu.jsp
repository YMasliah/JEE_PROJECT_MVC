<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="login" value="/actions/directory/login" />
<c:url var="logout" value="/actions/directory/logout" />
<c:url var="groupList" value="/actions/directory/group/list/1" />
<c:url var="passwordLost" value="/actions/directory/password/sendMail" />


<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${login}">Accueil</a> <a
				class="navbar-text" href="${groupList}">liste des groupes</a>
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

							<div class="input-group col-md-4">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<form:input class="form-control" path="id" type="number"
									value="" placeholder="Id" />
								<%-- 				<form:errors path="id" cssClass="alert alert-warning" element="div" /> --%>
							</div>
							<div class="input-group form-group col-md-4">
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

							<form:errors path="*" cssClass="alert alert-danger" element="div" />
						</form:form></li>
				</c:when>
				<c:otherwise>
					<li><a disable><span class="glyphicon glyphicon-user"></span>
							Salut, <c:out value="${user.name}" /></a></li>
					<li><a href="${logout}"><span
							class="glyphicon glyphicon-off"></span> Logout</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>

<div>
	<c:if test="${type_notify == 'danger'}">
		<div class="alert alert-<c:out value="${type_notify}"></c:out>">
			<c:out value="${notify}"></c:out>
		</div>
	</c:if>
</div>
<div class="form-group">
	<c:if test="${error_pwd == 'OK'}">
		<div class="alert alert-danger">
			<c:out value="${notify_pwd}"></c:out>

		</div>
	</c:if>
</div>

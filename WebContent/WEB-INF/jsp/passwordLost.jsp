<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="login"  value="/actions/directory/login" />
<c:url var="logout" value="/actions/directory/logout" />
<c:url var="newUser" value="/actions/directory/newUser" />


	<div class="container">
		<form:form method="POST" commandName="user" >

			<form:errors path="*" cssClass="alert alert-danger" element="div" />
			
			<div class="form-group">
				<label for="name">Id :</label>
				<form:input class="form-control" path="id"/>
				<form:errors path="id" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<form:input class="form-control" path="password" />
				<form:errors path="password" cssClass="alert alert-warning"
					element="div" />
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-info">Submit</button>
			</div>
		</form:form>
	</div> 

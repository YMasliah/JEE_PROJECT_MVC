<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="login"  value="/actions/directory/login" />
<c:url var="logout" value="/actions/directory/logout" />
<c:url var="newUser" value="/actions/directory/newUser" />

<c:out value="${user.name}" default="please login"/>
<c:choose>
	<c:when test="${empty user.name}"> <p><a href="${login}">Login</a></p> </c:when>
	<c:when test="${user.name =='No User'}"> 
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
	</div> <p><a href="${newUser}">new User</a></p>
	</c:when>
	<c:otherwise>
		<p><a href="${logout}">Logout</a></p>
	</c:otherwise>
</c:choose>

<%-- <c:if test="${empty user.name}"> <p><a href="${show}">Login</a></p> </c:if> --%>
<%-- <c:if test="${not empty user.name && user.name ==''}"> <p>toto</p> </c:if> --%>
<%-- <c:if test="${not empty user.name && user.name !=''}"> <p><a href="${logout}">Logout</a></p> </c:if> --%>

<%-- <form action="${show}" method="post"> --%>
<!-- 	<button>New User</button> -->
<!-- </form> -->
<%-- <jsp:useBean id="user" scope="session" class="directory.manager.User" > --%>
<!--     <p>Nouveau utilisateur !</p> -->
<%-- </jsp:useBean> --%>


<!-- <form action="edition" method="POST"> -->
<%-- <jsp:useBean id="person" scope="session" class="myapp.Person" > --%>
<!--     <p>Nouveau produit !</p> -->
<%-- </jsp:useBean> --%>


<!--   <label>Nom : </label> -->
<%--     <input type="text" name="nom" size="15" value='<%=person.getNom()%>'/>errourrrrrrr ici PS: je suis en rouge, mais sinon faut juste crée une classe exception mais la flemme<br/> --%>

<!--   <label>Prénom : </label> -->
<%--     <input type="text" name="prenom" size="15" value='<%=person.getPrenom()%>'/><br/> --%>

<!--   <input type="submit" name="boutonOK" value="Valider"/> -->
<!-- </form> -->

<!-- <div class="container"> -->
<!-- 		<h1>Edit Product</h1> -->

<%-- 		<form:form method="POST" commandName="user"> --%>

<%-- 			<form:errors path="*" cssClass="alert alert-danger" element="div" /> --%>

<!-- 			<div class="form-group"> -->
<!-- 				<label for="id">id:</label> -->
<%-- 				<form:input class="form-control" path="id" /> --%>
<%-- 				<form:errors path="id" cssClass="alert alert-warning" --%>
<%-- 					element="div" /> --%>
<!-- 			</div> -->
<!-- 			<div class="form-group"> -->
<!-- 				<label for="password">password:</label> -->
<%-- 				<form:input class="form-control" path="password" /> --%>
<%-- 				<form:errors path="password" cssClass="alert alert-warning" --%>
<%-- 					element="div" /> --%>
<!-- 			</div> -->
<!-- 			<div class="form-group"> -->
<!-- 				<button type="submit" class="btn btn-info">Submit</button> -->
<!-- 			</div> -->
<%-- 		</form:form> --%>
<!-- 	</div> -->



<!-- <a href="/actions/user">Nom d'une personne</a> -->

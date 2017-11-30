<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>


<c:url var="view" value="/actions/directory/person/view" />

<html>
<head>
<title>Hello :: Spring Application</title>
</head>
<body>	
<header>
		<c:import url="/WEB-INF/jsp/loginMenu.jsp">
			<c:param name="page" value="1" />
		</c:import>
	</header>
    <div class="container">
        <h1><c:out value="${group.name}"></c:out> group : bon ici. si l'user est identique a l'etudiant on peut modifier <br> sinon on peu juste afficher les valeurs des etudiants (4 valeurs) <br> et pour finir on peu rajouter un nouvel utilisateur </h1>
        <table class="table table-hover">
            <c:forEach items="${persons}" var="persona">
                <tr>
                    <td><a href="${view}?id=${persona.id}">
                        <c:out value="${persona.lastName}" />
                    </a></td>
                </tr>
            </c:forEach>
        </table>
<!--         <p> -->
<%--             <a class="btn btn-info" href="${edit}">add new person</a> --%>
<!--         </p> -->
    </div>
</body>
</html>
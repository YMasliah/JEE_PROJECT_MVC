<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/actions/directory/person/edit" />
<c:url var="view" value="/actions/directory/person/view" />

<html>
<head>
<title>Hello :: Spring Application</title>
</head>
<body>
    <div class="container">
        <h1><c:out value="${group.name}"></c:out> group : bon ici. si l'user est identique a l'etudiant on peut modifier <br> sinon on peu juste afficher les valeurs des etudiants (4 valeurs) <br> et pour finir on peu rajouter un nouvel utilisateur </h1>
        <table class="table table-hover">
            <c:forEach items="${group.persons}" var="person">
                <tr>
                    <td><a href="${view}?id=${person.id}">
                        <c:out value="${person.lastName}" />
                    </a></td>
                     <td><a class="btn btn-info" href="${edit}?id=${person.id}">
                        modification
                    </a></td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <a class="btn btn-info" href="${edit}">add new person</a>
        </p>
    </div>
</body>
</html>